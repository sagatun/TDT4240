# TDT4240

# Øving 2

Start Android Studio. Velg "Åpne" og naviger til mappen med øvingsoppgave. Velg "build.gradle" - oppdater hvis spurt.
Kan kjøres i Android Emulator - dessverre ikke full skjerm..

Kan kjøres som desktop-app med ferdigdefinert oppløsning.

Grafikk fra www.opengameart.org

https://opengameart.org/content/sky-background
https://opengameart.org/content/helicopter-2
https://opengameart.org/content/enemy-game-character-ufo-spaceship

## Step 1

Done

## Step 2

Helikopteret blir instansiert som en Singleton. Med å gjøre konstruktøren privat, og lage en static instans av klassen sikrer man at kun en isntans av klassen blir instansiert.
Jeg har laget den lazy slik at koden venter til behovet er der før Singleton lages.

## Step 3

Jeg valgte Observator

## Step 4

a)
Design Patterns: Observer, State, Template method, Abstract factory, Pipe-and-filter
Architectural Pattern: Model View Controler, Pipe-and-filter

Forskjellen på disse er hovedsaklig scope. Architetural Pattern påvirker hele kodebasen der Design Pattern fokuserer på lokale problemer - instansiering av objekter og den slags.

b)
Jeg har laget en Observer og Subject-interface (i roten av src) som jeg lar Helicopter.java og Ufo.java implementere. Det sikrer at jeg får metodene jeg behøver for å adde
Ufo til observatøren - som er helikopteret i dette eksemplet. Denne Observatøren brukes til å fortelle Ufoene når en ufo og helikopter krasjer. Da blir alle Ufoene veldig irriterte.

c)
Fordelen er at jeg får sendt en beskjed om endring til alle ufoene. Og det er uavhenigig av antall objekter jeg velger. Jeg kan dessuten i framtiden legge inn andre objekter som observatører. Fugler kanskje.
Ulempen er at jeg må behandle alle objekter likt. Jeg kan ikke la noen objekter ha oppførsel med unike metoder når en event trigges.
