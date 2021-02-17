import boto3
import os

class send_message:

    def __init__(self):
        pass

    def send(self, message):
        sqs = boto3.client('sqs')
        queue_url = os.getenv("queue_url")
        sqs.send_message(QueueUrl=queue_url, MessageBody=message)
