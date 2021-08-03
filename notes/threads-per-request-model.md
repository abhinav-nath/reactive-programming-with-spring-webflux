# Thread Per Request Model

The Servlet Container (Embedded Tomcat Server) has a Thread Pool to handle incoming HTTP requests.

For each HTTP request, Tomcat takes a Thread from the Thread Pool and assign that Thread to that HTTP request.
Each allocated thread has the responsibility of handling the lifecycle of each HTTP request.

* So one server can handle upto N requests at a given point of time
* Where N is the size of the Thread Pool
* Size of the Thread Pool is managed by below property:
  - `server.tomcat.max-threads`

By default it can handle **200** connections i.e. theoritically, it can handle only 200 concurrent requests at a time.

Even though we can override the default value of this property in applications.properties file, setting a large size is not a good idea!

* Each thread takes some memory
* Common stack size is 1 MB
* Higher the thread pool size, higher the memory consumption
* Applications really perform poor with less memory available


## How is it handled today?

* Load is handled today by _**Horizontal Scaling**_
* We simply spin-up multiple instances of the application to handle more load
* Kubernetes like container orchestration platform is used to achieve this
* This model is perfect but on the cloud it will be a costly setup