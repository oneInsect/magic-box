# coding=utf-8
import datetime
from typing import List

import motor.motor_asyncio

from orm.model import Chat, ChatLast, ChatCount
from pymongo import DESCENDING

client = motor.motor_asyncio.AsyncIOMotorClient(host="192.168.1.188",
                                                port=27017,
                                                username="chat",
                                                password="123456",
                                                authSource="chat")


database = client.chat
collection = database.chats


async def fetch_chats(chat_last: ChatLast) -> List[Chat]:
    chats = []
    cursor = collection.find({"user_name": chat_last.user_name}).sort('chat_time', DESCENDING).limit(chat_last.last_n)
    async for document in cursor:
        chats.append(Chat(**document))
    return chats


async def fetch_last_chats(user_name: str) -> List[Chat]:
    chats = []
    cursor = collection.find({"user_name": user_name}).sort('chat_time', DESCENDING).limit(20)
    async for document in cursor:
        chats.append(Chat(**document))
    return chats


async def create_chat(chat: Chat) -> Chat:
    await collection.insert_one(chat.model_dump())
    return chat


async def cnt_chats(user_name: str) -> int:
    # 获取当前时间
    now = datetime.datetime.now()
    zero_point = now.replace(hour=0, minute=0, second=0)
    count = await collection.count_documents({"type": "user",
                                              "user_name": user_name,
                                              "chat_time": {"$gt": zero_point}})
    return count


async def cnt_chats_half_minus(user_name: str) -> int:
    # 获取当前时间
    current_time = datetime.datetime.now()

    # 计算30秒前的时间点
    previous_time = current_time - datetime.timedelta(seconds=30)
    count = await collection.count_documents({"type": "user",
                                              "user_name": user_name,
                                              "chat_time": {"$gt": previous_time}})
    return count
