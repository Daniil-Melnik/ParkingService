<h1>Макет системы учёта операций парковки</h1>
<h2>Применяемые технологии</h2>
<ul>
  <li>Spring Boot</li>
  <li>Spring Cloud</li>
  <li>PostgreSQL</li>
  <li>Hibernate ORM</li>
  <li>
    Java
    <ul>
      <li>Locale</li>
      <li>Beans</li>
      <li>Annotations</li>
    </ul>
  </li>
</ul>
<h2>Архитектура</h2>
<p>Приложение предназначено для интеграции в распределённую систему с микросервисной архитектурой в виде отдельного микросервиса.</p>
<p>Состоит из трёх частей: сервер конечных точек, сервер конфигурации и базы данных. Код сервера конечных точек помещен в папке <a href="pr3_1">pr3_1</a>, сервера конфигураций в <a href="pr3_2">pr3_2</a>. Бэкап базы данных parking находится в файле <a href="parking_backup.sql">parking_backup.sql</a></p>
<h2>ПО при разработке</h2>
<ul>
  <li>ОС Windows 10 22h2 / Linux Ubuntu 24.04</li>
  <li>JDK 2025 <a href="https://download.oracle.com/java/25/latest/jdk-25_windows-x64_bin.msi">скачать</a></li>
  <li>PostgreSQL 18 <a href="https://sbp.enterprisedb.com/getfile.jsp?fileid=1260118">скачать</a></li>
  <li>IDE InteliJ Idea 2025 CE</li>
  <li>Postman v11.88.3</li>
  <li>Maven v3.9.14 <a href="https://dlcdn.apache.org/maven/maven-3/3.9.14/binaries/apache-maven-3.9.14-bin.zip">скачать</a></li>
</ul>
<h2>Запуск</h2>
<p>В ппапке <a href="pr3_jars">pr3_jars</a> находится два исполняемых jar-архива: configserver и centers. Для запуска необходимо: <ul>
  <li>Развернуть локально PostgreSQL, создать БД parking. Креды для подключения со стороны сервера конечных точек, которым должен отвечать сервер СУБД и БД:
  <ul>
    <li>источник - jdbc:postgresql://localhost:5432/parking</li>
    <li>логин - admin</li>
    <li>пароль - 12345678</li>
  </ul>
  </li>
  <li>Восстановить БД - <code>psql -d parking -U postgres -f путь\parking_backup.sql</code></li>
  <li>Запустить сервер конфигураций - <code>java -jar .\configserver-x.x.x-SNAPSHOT.jar</code></li>
  <li>Запустить сервер конечных точек - <code>java -jar .\centers-x.x.x-SNAPSHOT.jar</code></li>
</ul></p>
<h2>Взаимодействие с приложением</h2>
<p>Для отправки запросов и получения ответов применяется приложение Postman. Как пример, демонстрирующий форматы запросов, входящие в них значения header переменных можно рассмотреть <a href="https://daniil-melnik-7665589.postman.co/workspace/daniil-melnik's-Workspace~f87cf77f-5c45-4e25-85e1-458e9f8f8325/collection/49495678-95236611-e7a0-4b0a-af8d-00cb07a9f1fa?action=share&creator=49495678">рабочее пространство</a></p>
<p>Форматы запросов: <code>parkingCenters/<запрос></code>
<ul>
  <li>GET:
  <ul>
    <li><code>/node/{nodeName}</code></li>
    <li><code>/getByPlaceNo/{placeNo}</code></li>
    <li><code>/getByOperationID/{id}</code></li>
    <li><code>/findByCarRegNumber/{regNumber}</code></li>
    <li><code>/active</code></li>
    <li><code>/{parkingNodeName}/{parkingPlaceNo}/{id}/{carRegNum}</code></li>
  </ul>
  </li>
  <li>POST:
  <ul>
    <li><code>/{parkingNodeName}/{parkingPlaceNo}</code></li>
  </ul>  
  </li>
  <li>PUT:
    <ul>
      <li><code>/updParking</code></li>
    </ul>
  </li>
  <li>DELETE:
    <ul>
      <li><code>/deleteParkingOperation/{id}</code></li>
    </ul>
  </li>
</ul></p>
<p>Header-переменная <code>Accept-Language</code> может принимать значения: <code>ru, en, cs</code></p>


