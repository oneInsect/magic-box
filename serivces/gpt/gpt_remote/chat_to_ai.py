# coding=utf-8
import datetime
from typing import List, Union, Optional

from openai import OpenAI
import os
import time

NOT_GIVEN="NOT_GIVEN"

from orm.model import Chat, ChatMessage

API_BASE = "https://openrouter.ai/api/v1"
API_KEY = "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"
YOUR_SITE_URL = "local.x.com"
YOUR_APP_NAME = "ChatByX"
SYSTEM = "system"
ASSISTANT = "assistant"


client = OpenAI(base_url="https://openrouter.ai/api/v1", api_key=os.environ.get("OPENAI_API_KEY", API_KEY))


def chat_to_ai(chat: Chat, chat_messages: List[ChatMessage],
               stop: Union[Optional[str], List[str]] = "NOT_GIVEN") -> Chat:
    start_time = time.time()
    # send a ChatCompletion request to count to 100
    response = client.chat.completions.create(
        model='mistralai/mistral-7b-instruct:free',
        messages=[dict(chat_message) for chat_message in chat_messages],
        temperature=0,
        stream=True,
        n=1,
        stop=stop
    )
    # create variables to collect the stream of chunks
    collected_chunks = []
    collected_messages = []
    # iterate through the stream of events
    for chunk in response:
        collected_chunks.append(chunk)  # save the event response
        chunk_message = chunk.choices[0].delta.content  # extract the message
        collected_messages.append(chunk_message)  # save the message

    # clean None in collected_messages
    collected_messages = [m for m in collected_messages if m is not None]
    full_reply_content = ''.join([m for m in collected_messages])
    print(f"Full conversation received: {full_reply_content}")
    chat.message = full_reply_content
    chat.response_spent = time.time() - start_time
    chat.type = ASSISTANT
    chat.chat_time = datetime.datetime.now()
    return chat


def embeddings_to_ai(chat: Chat, chat_messages: List[ChatMessage]) -> Chat:

    return chat