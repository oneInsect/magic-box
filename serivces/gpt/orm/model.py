# coding=utf-8


# Pydantic allows auto creation of JSON Schemas from models
from pydantic import BaseModel
from datetime import datetime


class ChatBase(BaseModel):
    user_name: str


class ChatRequest(ChatBase):
    message: str


class Chat(ChatRequest):
    user_name: str
    message: str
    chat_time: datetime | None = None
    token: int | None = None
    middle_out_message: str | None = None
    response_spent: float | None = 0
    type: str | None = None


class ChatLast(BaseModel):
    user_name: str
    last_n: int


class ChatCount(BaseModel):
    user_name: str
    chat_cnt: int | None = None


class ChatHistory(BaseModel):
    text: str
    type: str


class ChatMessage(BaseModel):
    role: str
    content: str

    def __dict__(self):
        return {"role": self.role, "content": self.content}
