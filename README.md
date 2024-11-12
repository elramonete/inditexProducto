# InditexProducto
Proyecto realizado para la prueba de inditex

Para acceder a la base de datos al levantar el programa acceder a http://localhost:8050/h2-console

Los datos necesarios para ver la tabla son los siguientes:

Driver class: org.h2.Driver

JDBC url: jdbc:h2:mem:testdb

![img.png](img.png)

user: sa
rar
sin password

![image](https://user-images.githubusercontent.com/7735554/233590317-0035b22f-e5d6-41dd-beaf-9134589d8c40.png)

Teneis una interfaz swagger disponible para hacer las pruebas necesarias en http://localhost:8050/swagger-ui/

![image](https://user-images.githubusercontent.com/7735554/233591202-9f26d139-71a0-4464-8977-c7bc8a31ed0b.png)

El proyecto se ha realizado con arquitectura hexagonal,
En esta arquitectura hay 3 partes diferenciadas, el **domain**, donde se encuentra
el dominio de la **aplicacion**, luego la segunda es la **aplicacion**, que es   
el corazon del aplicativo y donde se encuentra la clase PriceService y las interfaces (port) que a su vez, se comunican con la capa de la **infraestructura**, y por ultimo
la **infraestructura** que es donde he metido tanto los repositorios con acceso a bbdd incluidas las entidades como los controladores a traves de los cuales se conecta la applicacion con el exterior.
Y el MapStruct, donde transformamos los DTO's a Entity's, y viceversa, pasando por el Dominio.

Muestro una imagen como tenemos estructurado el proyecto:

![img_4.png](img_4.png)

En la parte de los tests tenemos test unitarios con mockito para los servicios y los adaptadores de los repositorios y he utilizado RestAssured para hacer una prueba 
endToEnd de los controladores. 

Las 5 pruebas que indicais que hay que realizar en la prueba estan implementadas en la clase **PriceControllerAdapterTest**.

Hemos añadido cada vez que realicemos un commit al repositorio GitHub,
automaticamente realice un despliegue en Jenkins.

Añado una captura de pantalla :
![img_2.png](img_2.png)

Vemos que se despliega automáticamente nada más subir nuestro commit:
![img_3.png](img_3.png)
