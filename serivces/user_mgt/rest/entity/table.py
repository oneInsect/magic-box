
from sqlalchemy import create_engine, Table, Column, Integer, String, MetaData, ForeignKey


engine = create_engine('sqlite:///:memory:', echo=True)
metadata = MetaData()
