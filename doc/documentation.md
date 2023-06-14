# Java Inicial: Proyecto Final
## ABM (Alta-Baja-Modificacion) ABM

A continuación se explicará sobre el la instalación y ejecución de la aplicación presente, un sistema de facturación ABM, para generar facturas utilizando una arquitectura APIRESTful, con Spring Boot como framework.
El sistema permite generar clientes y productos, con la posibilidad de realizar consultas de lectura, modificar y eliminar, pudiendo realizar un CRUD de los mismos.
El sistema permite generar facturas, las mismas cuentan con detalles de facturación, pudiendo consultar las mismas. En este caso no se puede hacer un CRUD completo.

A continuación se adjunta el DER (Diagrama Entidad Relación) que representa el ABM.

![DER](./images/DER.jpg?raw=true)

Requisitos Previos
Antes de comenzar, asegúrate de tener lo siguiente instalado en tu entorno de desarrollo:

Java Development Kit (JDK) 8 o superior.
Un IDE de tu elección (este documento utilizara IntelliJ IDEA).
Maven o Gradle (este documento utilizará Maven).

### Paso 1: Configuración del Proyecto
Crea un nuevo proyecto de Spring Boot utilizando Spring Initializr o tu IDE preferido. Asegúrate de incluir las siguientes dependencias:
1. Spring Web
2. Spring Data JPA
3. H2 Database

Importa el proyecto en tu IDE y asegúrate de que todas las dependencias se hayan descargado correctamente.


### Paso 2: Configuración de la Base de Datos H2
1. Abre el archivo application.properties (o application.yml) en el directorio src/main/resources y agrega la siguiente configuración:

[application.properties](../src/main/resources/application.properties)

Esto habilitará la consola de H2 y configurará la conexión a la base de datos en memoria.

2. Opcionalmente, puedes ajustar la configuración de la base de datos según tus necesidades.

### Paso 3: Creación de Entidades y Repositorios
1. Crea las entidades de dominio de tu aplicación utilizando anotaciones JPA:

Para esta aplicacion, respetando el DER elaborado, se necesita la creacion de las entidades Product, Client, 
Invoice e InvoiceDetail dentro del package 'model'.<br> A las mismas se les aplica las annotations necesarias para el 
uso de spring boot, que las reconozca como tales y sea beneficioso el uso dependencias como spring JPA.

[Product](../src/main/java/com/springboot/facturacionfrasson/model/Product.java)<br>
[Client](../src/main/java/com/springboot/facturacionfrasson/model/Client.java)<br>
[Invoice](../src/main/java/com/springboot/facturacionfrasson/model/Invoice.java)<br>
[InvoiceDetail](../src/main/java/com/springboot/facturacionfrasson/model/InvoiceDetail.java)<br>

2. Crea interfaces de repositorio extendiendo JpaRepository de Spring Data JPA, por ejemplo:

Son responsables de interactuar con la capa de almacenamiento de datos, como una base de datos, para realizar operaciones de lectura y escritura.<br> Su función principal es abstraer la lógica de acceso a los datos y proporcionar métodos para realizar consultas y manipulaciones en la base de datos de manera eficiente.<br> Los repositorios suelen representar entidades o modelos del dominio de la aplicación y ofrecen una interfaz para realizar operaciones CRUD (crear, leer, actualizar y eliminar) en esos datos.

[ProductRepository](../src/main/java/com/springboot/facturacionfrasson/repository/ProductRepository.java)<br>
[ClientRepository](../src/main/java/com/springboot/facturacionfrasson/repository/ClientRepository.java)<br>
[InvoiceRepository](../src/main/java/com/springboot/facturacionfrasson/repository/InvoiceRepository.java)<br>
[InvoiceDetailRepository](../src/main/java/com/springboot/facturacionfrasson/repository/InvoiceDetailRepository.java)<br>

### Paso 4: Capa de Servicios
1. Los servicios son componentes que encapsulan la lógica de negocio de una aplicación. Son responsables de coordinar y orquestar diversas operaciones y acciones dentro del sistema.<br> Los servicios definen una funcionalidad específica y suelen ser invocados por los controladores o por otros servicios. Su propósito es separar la lógica de negocio de los detalles de implementación y proporcionar una abstracción para que los controladores puedan utilizar la funcionalidad de manera coherente.

[ProductService](../src/main/java/com/springboot/facturacionfrasson/service/ProductService.java)<br>
[ClientService](../src/main/java/com/springboot/facturacionfrasson/service/ClientService.java)<br>
[InvoiceService](../src/main/java/com/springboot/facturacionfrasson/service/InvoiceService.java)<br>
[InvoiceDetailService](../src/main/java/com/springboot/facturacionfrasson/service/InvoiceDetailService.java)<br>

### Paso 5: Creación de Controladores
1. Son componentes que manejan las solicitudes y respuestas en una aplicación web.<br> Reciben las solicitudes del cliente, interactúan con los servicios y repositorios necesarios para satisfacer esas solicitudes y generan las respuestas correspondientes. 
2. Los controladores interpretan los datos de entrada proporcionados por el cliente, toman decisiones basadas en esa información y se encargan de devolver la respuesta adecuada al cliente, que puede ser en forma de HTML, JSON u otros formatos.<br> 
3. Los controladores también son responsables de manejar los errores y las excepciones que puedan ocurrir durante el proceso de manejo de la solicitud.

[ProductController](../src/main/java/com/springboot/facturacionfrasson/controller/ProductController.java)<br>
[ClienttController](../src/main/java/com/springboot/facturacionfrasson/controller/ClientController.java)<br>
[InvoiceController](../src/main/java/com/springboot/facturacionfrasson/controller/InvoiceController.java)<br>

### Paso 6: Ejecución de la Aplicación
1. Ejecuta la aplicación desde tu IDE o utilizando el siguiente comando Maven en la línea de comandos:

mvn spring-boot:run

2. Accede a la consola de H2 en tu navegador web utilizando la URL:

http://localhost:8888/h2-console

Asegúrate de que la URL de conexión sea la misma que configuraste en el archivo application.properties.

3. Puedes interactuar con la aplicación a través de las rutas definidas en los controladores.<br><br>

# EndPoinst
Son las URL (Uniform Resource Locators) a través de las cuales se accede a diferentes recursos o servicios en una aplicación o sistema. En el contexto de las API (Application Programming Interfaces), los endpoints representan las distintas funcionalidades o acciones que se pueden realizar sobre esos recursos.

Cada endpoint se define mediante una combinación de la URL base del servicio y una ruta específica que identifica la acción o recurso deseado. Cuando se realiza una solicitud HTTP a un endpoint específico, se espera una respuesta del servidor que puede contener datos, realizar alguna operación o devolver un código de estado.

Los EndPoinst brindados por este sistema son los siguientes:

## Client
<br>
POST/api/v1/client: Sirve para generar un cliente del Sistema.<br><br>
GET/api/v1/client: Sirve para listar todos los clientes generados hasta el momento.<br><br>
GET/api/v1/client/{id}: Sirve para obtener un cliente especifico mediante su identificador.<br><br>
PUT/api/v1/client/{id}: Sirve para obtener un cliente especifico mediante su identificador y actualizar uno o todos sus datos almacenados por los que se envian mediante la peticion.<br><br>
DELETE/api/v1/client/{id}: Sirve para borrar un cliente generado, mediante su identificador.<br><br>

## Product
<br>
POST/api/v1/product: Sirve para generar un producto del Sistema.<br><br>
GET/api/v1/product: Sirve para listar todos los productos generados hasta el momento.<br><br>
GET/api/v1/product/{id}: Sirve para obtener un producto especifico mediante su identificador.<br><br>
PUT/api/v1/product/{id}: Sirve para obtener un producto especifico mediante su identificador y actualizar uno o todos sus datos almacenados por los que se envian mediante la peticion.<br><br>
DELETE/api/v1/product/{id}: Sirve para borrar un producto generado, mediante su identificador.<br><br>

## Invoice
<br>
POST/api/v1/invoice: Sirve para generar una factura dentro del sistema.<br><br>
GET/{id}/client/{client_id}: Sirve para obtener una factura especifica, mediante su identificador. Necesita ademas del identificador unido del cliente al cual la factura esta asociada.<br><br>
GET/getInvoiceByClientId/{client_id}: Sirve para obtener todas las facturas asociadas a un cliente, mediante el identificador del mismo.