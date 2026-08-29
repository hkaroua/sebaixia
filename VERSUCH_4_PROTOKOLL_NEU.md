Versuchsprotokoll Versuch 4 - Automaten

Praktikum: Digitaltechnik
Versuch: 4 - Automaten
Name: Precious Adaugo Nnamaka
Betreuer: Prof. Dr.-Ing. Peter Zipf

1 Ziel des Versuchs

Ziel des vierten Versuchs war die Entwicklung und praktische Umsetzung eines Automaten zur Steuerung eines Schrittmotors und eines Modellkrans. Der Kran sollte nach einem seriellen Startsignal eine Last aufnehmen, den Ausleger um 180 Grad bewegen, die Last wieder ablegen und anschliessend in die Ausgangsposition zurueckkehren. Zusaetzlich wurden eine Geschwindigkeitsrampe sowie ein lastabhaengiger Nothalt umgesetzt.

Der Versuch verdeutlichte, wie ein abstrakter Zustandsautomat in ein reales technisches System uebertragen wird. Dabei mussten sowohl die logische Zustandsfolge als auch praktische Aspekte wie Motordrehrichtung, Schrittzahl, Magnetsteuerung, Stromversorgung und Sicherheitsverhalten beachtet werden.

2 Verwendete Komponenten

Komponente | Verwendung
Arduino Uno | Implementierung des Automaten und serielle Steuerung
Schrittmotor | Drehbewegung des Kranauslegers
Treiberplatine | Ansteuerung der Motorwicklungen
Elektromagnet | Aufnehmen und Ablegen der Last
Labornetzteil | Versorgung mit 5,0 V und Strombegrenzung
Wokwi-Simulation | Vorpruefung mit Motor und LED als Magnet-Ersatz
Steckleitungen / USB | Signal-, Versorgungs- und Programmierschnittstellen

3 Pinbelegung

Die Signale des Arduino wurden mit der Treiberplatine und dem Elektromagneten verbunden. Die verwendete Pinbelegung lautete:

Funktion | Arduino-Pin | Treiber / Last
Motor A | D8 | Sig8
Motor B | D9 | Sig6
Motor C | D10 | Sig7
Motor D | D11 | Sig5
Elektromagnet | D12 | Steuerleitung Magnet

Die Treiberplatine wurde ueber VCC und GND versorgt. In der Grundstellung war der Magnet ausgeschaltet. Die Motorsignale wurden so gesetzt, dass der Motor nicht unkontrolliert weiterlief und der Aufbau in einem definierten Zustand blieb.

4 Theoretische Grundlagen

4.1 Schrittmotorsteuerung

Ein Schrittmotor bewegt sich nicht kontinuierlich, sondern in einzelnen Winkelschritten. Im Versuch wurde ein Vollschrittbetrieb verwendet. Bei einem Schrittwinkel von 1,8 Grad ergeben sich fuer eine Drehung von 180 Grad:

180 Grad / 1,8 Grad = 100 Schritte

Fuer eine halbe Umdrehung des Kranauslegers mussten daher 100 Schritte ausgefuehrt werden. Die Drehrichtung wurde durch die Reihenfolge der Motorsignale bestimmt. Die vier Ansteuerzustaende wurden zyklisch durchlaufen.

Zustand | A | B | C | D | naechster Zustand bei Richtung 0 | naechster Zustand bei Richtung 1
one | 0 | 1 | 0 | 1 | Three | Seven
Three | 1 | 0 | 0 | 1 | Five | One
Five | 1 | 0 | 1 | 0 | Seven | Three
Seven | 0 | 1 | 1 | 0 | One | Five

Die Funktion stepper(Direction_i, SPEED_i) steuerte die Weiterbewegung. Der Parameter Direction_i legte die Drehrichtung fest. Der Parameter SPEED_i bestimmte die Geschwindigkeit und wurde auf den Bereich von 0 bis 20 begrenzt. Die Wartezeit zwischen zwei Schritten wurde mit

delay = 40 ms - SPEED_i

festgelegt. Eine groessere Geschwindigkeit bedeutete somit eine kleinere Wartezeit zwischen den Schritten.

4.2 Moore-Automat

Der Modellkran wurde als Moore-Automat beschrieben. Bei einem Moore-Automaten haengen die Ausgaenge nur vom aktuellen Zustand ab, nicht direkt von den Eingangssignalen. Die Eingangssignale bestimmen lediglich, welcher Folgezustand eingenommen wird.

Im Versuch wurden folgende serielle Befehle verwendet:

Befehl | Bedeutung
s | Arbeitszyklus starten
e | Nothalt ausloesen
c | Nach einem Nothalt fortsetzen

Die wichtigsten Zustände des Automaten waren:

Zustand | Aktion / Ausgaenge | Uebergang
IDLE | Magnet aus, Motor in Grundstellung | s -> PICKUP
PICKUP | Magnet ein, Last aufnehmen | nach Wartezeit -> MOVE_TO_TARGET
MOVE_TO_TARGET | Magnet ein, Motor dreht zur Zielposition | nach 100 Schritten -> DROP_LOAD
DROP_LOAD | Motor stoppt, Magnet aus, Last ablegen | nach Wartezeit -> RETURN_HOME
RETURN_HOME | Magnet aus, Motor dreht zurueck | nach 100 Schritten -> IDLE
EMERGENCY_WITH_LOAD | Sofortiger Stopp, Magnet bleibt ein | c -> vorheriger Zustand
EMERGENCY_EMPTY | Sofortiger Stopp, Magnet bleibt aus | c -> vorheriger Zustand

Der Nothalt wurde lastabhaengig umgesetzt. Wenn sich eine Last am Magneten befand, blieb der Magnet im Notzustand eingeschaltet, damit die Last nicht herunterfaellt. Wenn keine Last aufgenommen war, blieb der Magnet ausgeschaltet.

4.3 Geschwindigkeitsrampe

Damit der Kran nicht zu ruckartig anfaehrt oder abbremst, wurde eine Geschwindigkeitsrampe verwendet. Waehrend einer Fahrt mit 100 Schritten stieg die Geschwindigkeit in den ersten 20 Schritten an, blieb im mittleren Bereich hoch und wurde in den letzten 20 Schritten wieder reduziert.

Dadurch wurde das hohe Traegheitsmoment des Kranauslegers beruecksichtigt. Die Rampe verringerte die Wahrscheinlichkeit von Schrittverlusten und sorgte fuer eine ruhigere Bewegung.

5 Versuchsdurchfuehrung

Zunaechst wurde die Steuerung in Wokwi simuliert. Dabei wurde der Elektromagnet durch eine LED ersetzt. So konnte die Zustandslogik getestet werden, bevor der reale Aufbau angeschlossen wurde.

Anschliessend wurden Arduino, Treiberplatine und Schrittmotor entsprechend der Pinbelegung verbunden. Das Labornetzteil wurde auf 5,0 V eingestellt und die Strombegrenzung gemaess Vorgabe gesetzt. Danach wurden der Elektromagnet und die Kranmechanik angeschlossen. Vor dem Start wurde geprueft, ob sich der Ausleger frei bewegen kann und keine Leitungen die Bewegung behindern.

Der Arbeitszyklus wurde ueber den seriellen Monitor mit dem Befehl s gestartet. Beobachtet wurden das Aufnehmen der Last, die Drehung um 180 Grad, das Ablegen der Last, die Rueckfahrt und das Erreichen der Grundstellung.

Zusaetzlich wurde der Nothalt getestet. Dazu wurde waehrend der Bewegung der Befehl e gesendet. Der Motor stoppte sofort. Bei einem Nothalt mit Last blieb der Magnet eingeschaltet. Bei einem Nothalt ohne Last blieb der Magnet ausgeschaltet. Mit dem Befehl c konnte der Ablauf anschliessend fortgesetzt werden.

6 Ergebnisse

Pruefpunkt | Beobachtung | Bewertung
Motortest | 100 Schritte vorwaerts und 100 Schritte zurueck, Ausgangslage wieder erreicht | erfuellt
Geschwindigkeit | sanftes Anfahren und Abbremsen durch Rampe | erfuellt
Kranzyklus | Aufnehmen, 180-Grad-Drehung, Ablegen und Rueckfahrt | erfuellt
Grundstellung | Magnet aus, Motor in definierter Grundstellung | erfuellt
Nothalt mit Last | Motor stoppt, Magnet bleibt aktiv | erfuellt
Nothalt ohne Last | Motor stoppt, Magnet bleibt inaktiv | erfuellt

Die Versorgung erfolgte mit 5,0 V. Die beobachtete Stromaufnahme lag bei etwa 0,03 A. Die Funktionspruefung zeigte, dass der Automat die vorgesehenen Zustaende in der richtigen Reihenfolge durchlief.

Abbildung 1: Kranmechanik und Schrittmotor.
Abbildung 2: Arduino und Treiberplatine.
Abbildung 3: Versorgung mit 5,0 V und gemessener Stromaufnahme.
Abbildung 4: Gesamtaufbau mit Ausleger.

7 Auswertung

Die Schrittmotorsteuerung setzte die vier Vollschrittzustaende in beiden Drehrichtungen korrekt um. Die Berechnung von 100 Schritten fuer eine Drehung um 180 Grad stimmte mit der beobachteten Bewegung des Kranauslegers ueberein. Nach gleich vielen Schritten vorwaerts und rueckwaerts erreichte der Ausleger wieder die Ausgangsposition.

Der Moore-Automat trennte die einzelnen Betriebsphasen klar voneinander. In jedem Zustand waren die Ausgaenge eindeutig festgelegt. Dadurch war das Verhalten des Systems gut nachvollziehbar: Im Zustand PICKUP wurde die Last aufgenommen, in MOVE_TO_TARGET wurde sie transportiert, in DROP_LOAD abgelegt und in RETURN_HOME wurde der Kran zurueckgefahren.

Die Geschwindigkeitsrampe war fuer den praktischen Aufbau wichtig, da der Ausleger nicht abrupt beschleunigt oder abgebremst wurde. Dadurch wurde die mechanische Belastung reduziert und die Wahrscheinlichkeit von Schrittverlusten verringert.

Besonders wichtig war die Unterscheidung der beiden Nothaltzustaende. Bei einem Nothalt mit Last blieb der Magnet aktiv, sodass die Last nicht herunterfallen konnte. Bei einem Nothalt ohne Last blieb der Magnet ausgeschaltet. Damit wurde der Sicherheitsaspekt sinnvoll in die Zustandslogik integriert.

8 Fehlerbetrachtung

Moegliche Fehlerquellen bei diesem Versuch waren:

1. Vertauschte Motorleitungen koennen zu falscher Drehrichtung oder unruhiger Bewegung fuehren.
2. Eine zu hohe Schrittgeschwindigkeit kann Schrittverluste verursachen.
3. Ohne Rampe kann der Ausleger durch seine Traegheit ruckartig starten oder stoppen.
4. Eine falsch gesetzte Strombegrenzung am Labornetzteil kann die Funktion beeintraechtigen oder Bauteile belasten.
5. Lose Steckverbindungen koennen zu Aussetzern bei Motor oder Magnet fuehren.
6. Wenn der Nothaltzustand nicht korrekt speichert, ob eine Last vorhanden ist, kann der Magnet im falschen Moment ausgeschaltet werden.
7. Mechanische Reibung oder blockierende Leitungen koennen die Bewegung des Krans beeinflussen.

9 Fazit

Der Versuch zeigte, wie ein Zustandsautomat zur Steuerung eines realen technischen Systems eingesetzt werden kann. Die Schrittmotorsteuerung funktionierte in beiden Drehrichtungen und die berechneten 100 Schritte fuer eine 180-Grad-Drehung konnten praktisch genutzt werden. Der Moore-Automat fuehrte den Kranzyklus mit Aufnehmen, Transportieren, Ablegen und Rueckfahrt in der richtigen Reihenfolge aus.

Die Geschwindigkeitsrampe verbesserte das Bewegungsverhalten des Krans und verringerte die Gefahr von Schrittverlusten. Der lastabhaengige Nothalt stellte sicher, dass der Motor sofort stoppt und der Magnet nur dann eingeschaltet bleibt, wenn eine Last gehalten werden muss. Insgesamt wurde deutlich, dass Automaten nicht nur fuer theoretische Zustandsdiagramme geeignet sind, sondern auch eine klare und sichere Struktur fuer praktische Steuerungsaufgaben liefern.
