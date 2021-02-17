resource "aws_sqs_queue" "sqs_example_terraform" {
  name = "sqs-example-terraform"
  delay_seconds = 90
  max_message_size = 2048
  message_retention_seconds = 86400
  receive_wait_time_seconds = 10
  redrive_policy = jsonencode({
    deadLetterTargetArn = aws_sqs_queue.sqs_example_terraform_dlq.arn
    maxReceiveCount = 4
  })

  tags = {
    name = "first-sqs-terraform"
  }
}

resource "aws_sqs_queue" "sqs_example_terraform_dlq" {
  name = "sqs-example-terraform-dlq"
  delay_seconds = 90
  max_message_size = 2048
  message_retention_seconds = 86400
  receive_wait_time_seconds = 10

  tags = {
    name = "first-sqs-terraform-dlq"
  }
}

resource "aws_sns_topic_subscription" "sns_topic_subscription" {
  topic_arn = aws_sns_topic.sns_topic_terraform.arn
  protocol  = "sqs"
  endpoint  = aws_sqs_queue.sqs_example_terraform.arn
}