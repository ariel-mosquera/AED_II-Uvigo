# Actividades de AED2
## Índice

* [1. Introducción](#1-introducción)
* [2. Descarga del proyecto](#2-descarga-del-proyecto)
  * [2.1. Requisitos Previos]((#21-requisitos-previos))
  * [2.2. Pasos para clonar el proyecto](#22-pasos-para-clonar-el-proyecto)
    * [2.2.1. Desde VS Code](#221-desde-vscode)
    * [2.2.2. Desde línea de comandos](#222-desde-linea-de-comandos)
* [3. Trabajar con el proyecto](#3-trabajar-con-el-proyecto)
  * [3.1. Estructura de paquetes del proyecto](#31-estructura-de-paquetes-del-proyecto)
  * [3.2. Estructura de clases de un paquete](#32-estructura-de-clases-de-un-paquete)
  * [3.3. Codificación](#33-codificacion)
  * [3.4. Prueba](#34-prueba)
    * [3.4.1. Main](#341-main)
    * [3.4.2. Clase de prueba](#342-clase-de-prueba)

------

## 1. Introducción

Este proyecto contiene las plantillas para la resolución de todos los boletines de ejercicios que se van a trabajar a lo largo del curso en la asignatura AEDII.

En primer lugar se explican los pasos para clonar un proyecto directamente desde VS Code o mediante la línea de comandos.

Por último, se detalla la estructura de paquetes y clases del proyecto *activities2*, y se describen las diferentes formas de codificación y prueba de los ejercicios, incluyendo tanto la ejecución de clases de prueba como métodos específicos.

------

## 2. Descarga del proyecto

A continuación, se indican los pasos a seguir para clonar un proyecto desde VS Code.

### 2.1 Requisitos Previos

- Asegúrate de tener correctamente instalado Java y VS Code.

  - Para verificar la instalación de Java, abre un terminal y ejecuta:

    ```
    java -version
    ```

  - Si no está instalado, agrega OpenJDK (instalación en Linux) o descarga el instalador de JDK (instalación en Windows, desde https://www.oracle.com/java/technologies/downloads/ )

- Conexión a internet para descargar el proyecto.

### 2.2 Pasos para clonar el Proyecto

#### 2.2.1 Desde VS Code

1. Abre **Visual Studio Code** en tu ordenador.

2. Presiona 

   ```
   Ctrl + Shift + P 
   ```

3. Escribe y selecciona:

   ```
   Git: Clone
   ```

4. Introduce la URL https://github.com/esei-aed/activities2.

5. Selecciona la carpeta de tu ordenador en donde vas a clonar el proyecto, haciendo clic en **Select as Repository Destination**

6. VS Code preguntará si deseas **abrir el repositorio clonado**. Selecciona **Open**


#### 2.2.2. Desde línea de comandos

Para poder clonar el proyecto desde línea de comandos sigue los pasos que se indican:

1. Abre VS Code

2. Ve a **Terminal -> New Terminal**

3. Asegúrate de tener instalado Git. 

   ```bash
   git --version
   ```

   Si no lo tienes, descárgalo desde https://git-scm.com/

4. Sitúate en la carpeta donde quieras descargar el proyecto.

5. Ejecuta el siguiente comando para clonar el proyecto:

   ```bash
   git clone https://github.com/esei-aed/activities2 
   ```

 Una vez clonado, puedes abrir el proyecto en **VS Code **y comenzar a trabajar.

------

## 3. Trabajar con el proyecto

### 3.1. Estructura de paquetes del proyecto

El proyecto *activities2*, en la carpeta *src/main*, contiene varios paquetes nombrados como `es.uvigo.esei.aed2.activityX`, donde `X` se sustituye por un número que identifica a cada uno de los boletines de ejercicios de la asignatura, cuyo enunciado está disponible en Moovi. Todos los boletines de ejercicios se trabajarán en el proyecto *activities2.

Cada boletín contiene varios ejercicios para resolver:

- En algunos casos, todos los ejercicios del boletín se resolverán en **una única clase java**, como es el caso del boletín 2, que se implementará íntegramente en la clase Activity2 del paquete `es.uvigo.esei.aed2.activity2`. 
- En otros casos, el boletín se revolverá en **varias clases**, como es el caso del boletín 3, donde los ejercicios se encuentran en paquetes distintos (`es.uvigo.esei.aed1.activity3.implementation`, `es.uvigo.esei.aed2.activity3.use`...) . En ese caso se nombran como `es.uvigo.esei.aed2.activityX.nameClass`, donde `nameClass` es un nombre representativo del ejercicio. 

### 3.2. Estructura de clases de un paquete

El proyecto *activities2*, en la carpeta *src/main*, contiene dos tipos de paquetes:

- **Paquetes con una sola clase**, como el paquete `es.uvigo.esei.aed2.activity2`, que contiene la clase Activity2.java, donde están los encabezados de los métodos a codificar para resolver todos los ejercicios del boletín 2. 

- **Paquetes con más de una clase**, como el paquete `es.uvigo.esei.aed2.activity4`, que contiene todas las clases necesarias para resolver un ejercicio del boletín 4, estando algunas de ellas ya codificadas para agilizar y facilitar la resolución del ejercicio. 


### 3.3. Codificación 

Para resolver los ejercicios de un boletín concreto:

1. Selecciona el paquete que se corresponde con el número de boletín a resolver (ejemplo, `es.uvigo.esei.aed2.activity2` para trabajar con el boletín 2).
2. Abre la clase donde vas a codificar los ejercicios ( ejemplo, `Activity2`).
3. Lee el enunciado del boletín y resuelve cada ejercicio planteado codificando el método correspondiente en la clase adecuada (ejemplo, método `isComplet`). Cada una de las clases ya incorpora la cabecera del método a codificar.

### 3.4. Prueba

Las clases de prueba están situadas en la carpeta *src/test* del proyecto *activities2*. Esta carpeta también se encuentra organizada en paquetes que se corresponden con los boletines. En cada paquete hay, por lo menos, una clase de prueba que se identifica porque su nombre termina con el sufijo *TestCase* (ejemplo, `Activity2TestCase` o `LinkedBinaryTreeTestCase`). 

Para **ejecutar una clase de prueba**:

1. Haz clic derecho en dicha clase (ejemplo `Activity2TestCase` ).
2. Selecciona **Run Test**.

Es posible **ejecutar un método específico de una clase de prueba** mediante el siguiente procedimiento:

1. Abre la clase de prueba (ejemplo, `Activity2TestCase`).
2. Localiza el método de prueba específico a ejecutar. El método debe estar anotado con `@Test` (ejemplo, `testCompleteEmptyTree`).
3. Aparece un icono de play ▶ al lado del nombre del método. Haz Clic en ese icono para ejecutar solo ese test.
4. También puedes hacer clic derecho sobre el icono de play y seleccionar **Run Test** o **Debug Test**.

