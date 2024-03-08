# coding=utf-8
import datetime
from typing import List

from tiktoken import encoding_for_model

from gpt_remote.chat_to_ai import chat_to_ai, embeddings_to_ai
from orm.database import create_chat, fetch_chats, cnt_chats, cnt_chats_half_minus, fetch_last_chats
from orm.model import Chat, ChatLast, ChatCount, ChatMessage, ChatBase

MAX_TOKEN = 8192
DAY_MAX_CHAT = 30
HALFMINUS_MAX_CHAT = 3
USER = "user"


async def get_response_and_save(chat: Chat) -> (bool, str):
    check_result, message = await check_cnt(chat.user_name)
    if not check_result:
        return check_result, message
    chat_messages = await build_chat(chat)
    chat = await create_chat(chat)
    messages = [{"role": "system", "content": "请使用中文回答并符合以下要求：1.内容在100字以内"}]
    messages.extend(chat_messages)
    chat = chat_to_ai(chat, messages)
    chat.token = encode_token(chat.message)
    chat = await create_chat(chat)
    return True, chat.message


async def build_chat(chat: Chat) -> List[ChatMessage]:
    chat.type = USER
    middle_out(chat)
    chats = await fetch_chats(ChatLast(user_name=chat.user_name, last_n=50))
    chat_messages = build_messages(chat, chats)
    chat.chat_time = datetime.datetime.now()
    return chat_messages


def build_messages(chat: Chat, chats: List[Chat]) -> List[ChatMessage]:
    chat_messages = [ChatMessage(role=chat.type, content=chat.message)]
    tmp_token = chat.token
    for _chat in chats:
        tmp_token += _chat.token
        if tmp_token > MAX_TOKEN:
            break
        chat_messages.append(ChatMessage(role=_chat.type, content=_chat.message))
    chat_messages.reverse()
    return chat_messages


def build_messages_add_time(chat: Chat, chats: List[Chat]) -> List[ChatMessage]:
    chat_messages = [ChatMessage(role=chat.type, content=chat.message)]
    tmp_token = chat.token
    for _chat in chats:
        tmp_token += _chat.token
        if tmp_token > MAX_TOKEN:
            break
        chat_messages.append(ChatMessage(role=_chat.type,
                                         content=_chat.message
                                                 + "时间："
                                                 + _chat.chat_time.strftime("%Y-%m-%d %H:%M:%S")))
    chat_messages.reverse()
    return chat_messages


async def get_user_chat_history_view(chat_last: ChatLast) -> List[Chat]:
    chats = await fetch_chats(chat_last)
    return chats


async def get_chat_status_today_view(chat_cnt: ChatCount) -> ChatCount:
    chat_cnt.chat_cnt = await cnt_chats(chat_cnt.user_name)
    return chat_cnt


async def get_ai_chat_response_advanced_view(chat_base: ChatBase) -> str:
    chats = await fetch_last_chats(chat_base.user_name)
    chat = Chat(user_name=USER, type=USER, message="我现在的情绪倾向是正向、负向还是中性？")
    chat.token = encode_token(chat.message)
    # 拼接起来
    chat_messages = build_messages(chat, chats)
    chat_messages.insert(0, ChatMessage(role="system", content="你是一个只会使用中文回答问题的助理。"))
    chat = embeddings_to_ai(chat, chat_messages)
    return chat.message


async def get_user_behavior_view(chat_base: ChatBase) -> str:
    chats = await fetch_last_chats(chat_base.user_name)
    chat = Chat(user_name=USER, type=USER, message="总结一下我们的聊天内容：1.我们讨论的主题是什么？2.我活跃的时间段")
    chat.token = encode_token(chat.message)
    # 拼接起来
    chat_messages = build_messages_add_time(chat, chats)
    chat_messages.insert(0, ChatMessage(role="system", content="你是一个只会使用中文回答问题的助理。"))
    chat = chat_to_ai(chat, chat_messages)
    return chat.message


async def check_cnt(user_name: str) -> (bool, str):
    cnt = await cnt_chats(user_name)
    if cnt > DAY_MAX_CHAT:
        return False, "您今天的请求次数已经超过限制！"
    half_minus_cnt = await cnt_chats_half_minus(user_name)
    if half_minus_cnt > HALFMINUS_MAX_CHAT:
        return False, "您30s内的请求次数已经超过限制！"
    return True, "OK"


def middle_out(chat: Chat):
    encoder = encoding_for_model('gpt-3.5-turbo')
    tokens = encoder.encode(chat.message)
    chat.token = len(tokens) + 22
    if chat.token > MAX_TOKEN:
        tokens = tokens[:4000] + tokens[chat.token-4192:]
        print("new token length" + str(len(tokens)))
        chat.middle_out_message = encoder.decode(tokens)


def encode_token(message: str) -> int:
    encoder = encoding_for_model('gpt-3.5-turbo')
    tokens = encoder.encode(message)
    return len(tokens)
