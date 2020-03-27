# s3-localstack-example
Example of writing to a local Amazon S3 bucket using [localstack](https://github.com/localstack/localstack).

## Building the Example
Run the following command to build the example application:

    ./gradlew clean build
    
## Running the Example
Follow the steps below to run the example application:

1. In the `localstack` directory, run the following commands to start localstack mock services:

        docker-compose up
        
2. Run the following command to execute the example application:

        ./gradlew run
        
    If successful, you will see the following in the Gradle console:

        > Task :run
        [main] INFO example.localstack.s3.Main - Running the Upload Example...
        [main] INFO example.localstack.s3.Main - Upload Example Complete
        [main] INFO example.localstack.s3.Main - Running the Multipart Upload Example...
        [main] INFO example.localstack.s3.Main - Multipart Upload Example Complete
        
    and you will see the following in the localstack console:
    
        localstack    | 2020-03-27 20:31:41,054:API: 127.0.0.1 - - [27/Mar/2020 20:31:41] "PUT /com.github.gregwhitaker.example/ HTTP/1.1" 200 -
        localstack    | 2020-03-27 20:31:41,245:API: 127.0.0.1 - - [27/Mar/2020 20:31:41] "PUT /com.github.gregwhitaker.example/basic/cat.jpeg HTTP/1.1" 200 -
        localstack    | 2020-03-27 20:31:41,370:API: 127.0.0.1 - - [27/Mar/2020 20:31:41] "POST /com.github.gregwhitaker.example/multipart/cat.jpeg?uploads HTTP/1.1" 200 -
        localstack    | 2020-03-27 20:31:41,517:API: 127.0.0.1 - - [27/Mar/2020 20:31:41] "PUT /com.github.gregwhitaker.example/multipart/cat.jpeg?uploadId=FpLND9meIT1KqJ4R6lsi78GRh6kWQI2uRPrkcEbH0tZnBfgVu9TOJzN7lg&partNumber=1 HTTP/1.1" 200 -
        localstack    | 2020-03-27 20:31:41,652:API: 127.0.0.1 - - [27/Mar/2020 20:31:41] "POST /com.github.gregwhitaker.example/multipart/cat.jpeg?uploadId=FpLND9meIT1KqJ4R6lsi78GRh6kWQI2uRPrkcEbH0tZnBfgVu9TOJzN7lg HTTP/1.1" 200 -

## Bugs and Feedback
For bugs, questions, feedback, and discussions please use the [Github Issues](https://github.com/gregwhitaker/s3-localstack-example/issues).

## License
MIT License

Copyright (c) 2020 Greg Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.