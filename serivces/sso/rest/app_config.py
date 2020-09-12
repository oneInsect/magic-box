
import os

from redis import Redis
from flask_session import Session
from flask import Flask


APP = Flask(__name__)


APP.config['SESSION_TYPE'] = 'redis'   #session存储格式为redis
APP.config['SESSION_REDIS'] = Redis(    #redis的服务器参数
    host='127.0.0.1',                 #服务器地址
    port=6379)                           #服务器端口

APP.config['SESSION_USE_SIGNER'] = True   #是否强制加盐，混淆session
APP.config['SECRET_KEY'] = os.urandom(24)  #如果加盐，那么必须设置的安全码，盐
APP.config['SESSION_PERMANENT'] = False  #sessons是否长期有效，false，则关闭浏览器，session失效
APP.config['PERMANENT_SESSION_LIFETIME'] = 3600   #session长期有效，则设定session生命周期，整数秒，默认大概不到3小时。
Session(APP)