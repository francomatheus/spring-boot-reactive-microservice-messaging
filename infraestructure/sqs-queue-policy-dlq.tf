resource "aws_sqs_queue_policy" "sqs_queue_policy-dlq" {
  queue_url = aws_sqs_queue.sqs_example_terraform_dlq.id

  policy = <<POLICY
{
  "Version": "2012-10-17",
  "Id": "sqspolicy",
  "Statement": [
    {
      "Sid": "First",
      "Effect": "Allow",
      "Principal": "*",
      "Action": "sqs:SendMessage",
      "Resource": "${aws_sqs_queue.sqs_example_terraform_dlq.arn}"
    }
  ]
}
POLICY
}