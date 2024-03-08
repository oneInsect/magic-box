# coding=utf-8
from typing import List

import uvicorn
from fastapi import FastAPI, status, Response

from orm.model import Chat, ChatLast, ChatCount, ChatRequest, ChatHistory, ChatBase
from views.views import get_response_and_save, get_user_chat_history_view, get_chat_status_today_view, \
    get_user_behavior_view

app = FastAPI()


@app.post("/get_ai_chat_response")
async def get_ai_chat_response(chat_request: ChatRequest, response: Response) -> str:
    """
    `get_ai_chat_response` 提供了与AI进行沟通的能力，并且支持多轮对话，多轮对话的长度取决于token的余量。

    单个用户调用存在次数限制，单日限制30次，30s内限制3次，该接口的入参与出参信息：

    |    入参    | 说明 |
    | ---------- | --- |
    | user_name |  聊天人的名字，因未做用户认证，本系统使用该字段来标识用户 |
    | message       |  用户的聊天内容 |

    |    出参    | 说明 |
    | ---------- | --- |
    | message |  AI对聊天的回答，如果超过次数限制将会返回对应的限制信息 |

    """
    chat = Chat(user_name=chat_request.user_name, message=chat_request.message)
    check_result, message = await get_response_and_save(chat)
    if not check_result:
        response.status_code = status.HTTP_401_UNAUTHORIZED
    return message


@app.post("/get_user_chat_history")
async def get_user_chat_history(chat_last: ChatLast) -> List[ChatHistory]:
    """
    `get_user_chat_history` 提供了对用户聊天历史的查询能力。

    |    入参    | 说明 |
    | ---------- | --- |
    | user_name |  需要查询聊天记录的用户名称 |
    | last_n       |  需要查询的聊天记录数量 |

    |    出参    | 说明 |
    | ---------- | --- |
    | type |  聊天输出方：user/ai |
    | text |  聊天内容 |

    """
    chats = await get_user_chat_history_view(chat_last)
    return [ChatHistory(text=chat.message, type=chat.type) for chat in chats]


@app.post("/get_chat_status_today")
async def get_chat_status_today(chat_cnt: ChatCount) -> ChatCount:
    """
    `get_user_chat_history` 提供了对用户聊天历史的查询能力。

    |    入参    | 说明 |
    | ---------- | --- |
    | user_name |  需要查询聊天次数的用户名称 |

    |    出参    | 说明 |
    | ---------- | --- |
    | user_name |  用户名称 |
    | chat_cnt |  当天的聊天次数 |

    """
    chats = await get_chat_status_today_view(chat_cnt)
    return chats


@app.post("/get_ai_chat_response_advanced")
async def get_ai_chat_response_advanced(chat_base: ChatBase) -> str:
    """
    `get_ai_chat_response_advanced` 目前的思路有两个：

    1.使用openai的接口来判断情绪倾向，但效果不理想
    2.使用百度的情感分类来实现，安装上有问题，还在解决。

    |    入参    | 说明 |
    | ---------- | --- |
    | user_name |  需要查询情感倾向的用户名称 |

    |    出参    | 说明 |
    | ---------- | --- |
    | message |  用户的情绪倾向 |

    """
    # message = await get_ai_chat_response_advanced_view(chat_base)
    return "暂未实现"


@app.post("/get_user_behavior")
async def get_user_behavior(chat_base: ChatBase):
    """
      `get_user_behavior` 返回用户聊天的主题摘要和时间段信息：

      |    入参    | 说明 |
      | ---------- | --- |
      | user_name |  需要查询总结的用户名称 |

      |    出参    | 说明 |
      | ---------- | --- |
      | message |  用户聊天的主题摘要和时间段信息 |

      """
    message = await get_user_behavior_view(chat_base)
    return message


if __name__ == "__main__":
    uvicorn.run("main:app", host="127.0.0.1", port=5000)
