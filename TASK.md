# "*Prime Searcher*" - Taskdescription

## Einführung
Das Beispiel soll eine einfache Verwendung von bestehendem Java-Code für ein Web-Deployment schulen.

## Ziele
Die einfache Umsetzung eines Primzahlen-Algorithmus soll mittels Web-Applikation für Zugriffe von außen bereitgestellt werden. Dabei soll die Verwendung eines Web-Frameworks geschult werden. Die Trennung der Anzeige und Bearbeitung mittels geeigneten Patterns soll ebenfalls trainiert werden.

## Voraussetzungen
* Grundverständnis von Java
* Grundverständnis von Web-Framework Implementierung
* Lesen und Umsetzen von APIs
* Automatisiertes Testen mittels Unit- und System-Tests

## Detailierte Ausgabenbeschreibung
Erstelle eine Web-Applikation welche nach Primzahlen forscht. Die Applikation soll auf ``/primes`` deployed sein. Die Ausgabe der Primzahlen wird mittels ``/primes/searcher`` aufgerufen. Richte eine manuelle bzw. automatische Redirection von ``/primes`` auf ``/primes/searcher`` ein. Solange die Bean im Web-Container läuft soll weiter nach Primzahlen geforscht werden. Falls das Servlet vom Container entfernt oder undeployed wurde, soll die Bean die Möglichkeit erhalten die Suche sauber zu beenden. Webbrowser-Clients können zu beliebigen Zeiten den aktuellen Stand abrufen! Achte auf eine sparsame Nutzung der Ressourcen des Applikationsservers!

Die Ausgabe des Searchers soll folgende Eckpunkte enthalten:

* Startzeit des Beans
* Die zuletzt gefundene Primzahl und die Systemzeit, wann diese gefunden wurde

Also zum Beispiel:

​		Started at Mon Apr 08 11:28:57 CET 2019
​		The last prime discovered was 937 at Mon Apr 08 11:29:01 CET 2019


Der Code wird mittels ``gradle bootRun`` und ``gradle test`` auf entsprechende Funktionalität überprüft.

## Bewertung
Gruppengrösse: 1 Person
### Grundanforderungen **überwiegend erfüllt**
- [ ] Implementierung der Startseite ``Primes`` mit Weiterleitung auf den Output
- [ ] Implementierung des ``Searcher`` Servlets (lastPrime, runningSince, lastTimeFoundPrime, terminatedFlag)
- [ ] einfacher Aufruftest mit der ``org.springframework.boot:spring-boot-starter-test`` Abhängigkeit

### Grundanforderungen **zur Gänze erfüllt**
- [ ] Strikte Trennung des HTML-Templates von funktionalem Code
- [ ] Implementierung der automatischen Weiterleitung nach entsprechender Zeit (z.B. 5sec)
- [ ] Test der funktionalen Anforderungen (Anzeige, Weiterleitung, Zeit)

## Quellen
* ["Serving Web Content with Spring MVC"; SpringBoot Guides; zuletzt besucht am 2019-04-08; online](https://spring.io/guides/gs/serving-web-content/)
* ["Context and Servlet Initialization Parameters"; Baeldung Guides; zuletzt besucht am 2019-04-08; online](https://www.baeldung.com/context-servlet-initialization-param)
* ["Testing the Web Layer"; SpringBoot Guides; zuletzt besucht am 2019-04-08; online](https://spring.io/guides/gs/testing-web/)
* ["Developing Web Applications"; SpringBoot Reference; zuletzt besucht am 2019-04-08; online](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-web-applications.html)
* ["RequestMapping"; Spring Framework API; zuletzt besucht am 2019-04-08; online](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html)
* ["ServletRegistrationBean"; SpringBoot API; zuletzt besucht am 2019-04-08; online](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/web/servlet/ServletRegistrationBean.html)

