from src.main.service.send_message import send_message

def handler(events, context):
  for event in events['Records']:
    sqs=send_message()
    message = event.get('Sns').get('Message')
    sqs.send(message)
    print(message)

