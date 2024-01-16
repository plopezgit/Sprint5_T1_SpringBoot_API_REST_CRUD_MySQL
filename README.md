# Sprint5_T1_Spring Boot_API_REST_CRUD_MySQL

We have an entity called  FlorEntity , which has the following properties:
- Integer id
- String name
- String country

We also have a DTO called  FlorDTO , which will have the same properties as the Branch entity, adding one:

- String type

This property, depending on the country of origin of the flower, must indicate whether it is "EU" or "Non-EU". To do this, you can have a private list in the same DTO (for example: List<String> countries), with the countries that are part of the EU.

Taking advantage of the JPA specification  , you will have to persist the  FlorEntity entity to a MySQL  database  , following the MVC pattern  .

The advice is   to use  FlorDTO in the Controller, and FlorEntity  in the Repository. The service layer will be in charge of translating between the two.

For this, depending on the main package, you will create a package structure, where you will place the classes you need:

- cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.controllers

- cat.itacademy.barcelonactiva.surnames.nom.s05.t01.n02.model.domain

- cat.itacademy.barcelonactiva.surnames.name.s05.t01.n02.model.dto

- cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.services

- cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.repository

The class located in the controllers package ( FlorController , for example), must be able to respond to the following requests to update and consult information:

- http://localhost:9001/flor/add
- http://localhost:9001/flor/update
- http://localhost:9001/flor/delete/{id}
- http://localhost:9001/flor/getOne/{id}
- http://localhost:9001/flor/getAll

As you can see, in the application.properties file, you must configure that the port to use is 9001.

Important

You will need to consider good API design practices, using correct error codes and responses in case of incorrect invocations. (You can check information about ResponseEntity).
You should include  swagger  so that any developer can get a quick idea of ​​the resources available in the API.

Very important

In addition to the Git link for the solved assignment,  you must include at least two links , other than the resources we have provided you with on campus, that have helped you, or could have helped you, to solve the entire assignment or some parts.