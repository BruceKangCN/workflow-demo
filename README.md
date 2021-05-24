# README

This is a tutorial for building a low-code CI/CD back-end based on **workflow engine** and **code generator** using `Java` with `spring-boot`, `camunda`, `quartz`, `freemarker`

## API

* `/` or `/camunda/app`: camunda web app login page, id and password are set in `application.yml`
* `/process/count`: count of all process instances deployed by `camunda`
* `/process/list`: a list of all process instances information
* `/city/all`: a table of all the cities stored in database, rendered by `freemarker`

## Requirements

* `JDK 8`
* `maven`
* IDE, `Intellij Idea` is recommended
* `Camunda Modeler`

## Credits

This demo is powered by the frameworks below, sorted by license type

### Apache 2.0

* spring-boot
* undertow
* camunda
* quartz
* freemarker

### MPL 2.0

* h2

### Eclipse Public License - v 2.0

* junit5
