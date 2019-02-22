# JSF FULL !!!

Everything required to run a JSF 2.2 project

This is implemented with Servlet 2.5 successfully including also PrimeFaces 6.2 and Jersey 2.27 for RESTful JSON Services, Some SQL Server for Persistence, and Apache POI for working with Excel files.

This project needs a Database called Ourownrecords and a table inside it called login with the folling ddl

create table login
(
  llavecod  varchar(35),
  usuario   varchar(25),
  ipaddress varchar(25),
  fecha     date,
  hora      time,
  note      varchar(100)
)

It also does some work with Synergetics Databases as well (This is a commercial product then Project could be run and just remove the code that works with Synergetics, which you can detect as soon as something breaks regarding SQL connection)

You can update the database server ip address in control.SQLControl.java because this is using plain JDBC centralized to one file.


This project anyway compiles without the database, it just will break in only a couple of windows that only show tables, it is not a big deal.
