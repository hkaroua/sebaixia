Versuchsprotokoll Versuch 2 - Kombinatorische Logik

Praktikum: Digitaltechnik
Versuch: 2 - Kombinatorische Logik
Name: Precious Adaugo Nnamaka
Betreuer: Prof. Dr.-Ing. Peter Zipf

1 Ziel des Versuchs

Ziel des zweiten Versuchs war die Realisierung und Ueberpruefung kombinatorischer Logikschaltungen. Im Mittelpunkt standen ein Volladdierer und dessen Erweiterung zu einem 4-Bit-Ripple-Carry-Addierer. Die Schaltung sollte zunaechst in Digital entworfen und simuliert, anschliessend mit 74er-Logikbausteinen auf dem Steckbrett aufgebaut und mit dem Arduino geprueft werden. Zusaetzlich wurde die Durchlaufzeit entlang des kritischen Pfades mit dem Oszilloskop untersucht.

2 Verwendete Bauteile und Geraete

Bauteil / Geraet | Verwendung
Arduino Uno | Erzeugung und Auswertung der Eingangskombinationen
74LS86 | XOR-Gatter fuer Summenbildung
74LS08 | AND-Gatter fuer Uebertragsbildung
74LS32 | OR-Gatter fuer Zusammenfuehrung der Teiluebertraege
Steckbrett und Jumper-Leitungen | Aufbau der Schaltung
LEDs mit Vorwiderstaenden | Anzeige der Ausgangszustaende
Digitales Oszilloskop | Messung der Durchlaufzeit
Digital-Datei corrected_2.dig | Simulation und Dokumentation des Schaltungsentwurfs

3 Theoretische Grundlagen

Ein Halbaddierer addiert zwei einstellige Binaerzahlen A und B. Er besitzt den Summenausgang S und den Uebertragsausgang Co. Die zugehoerigen Funktionen lauten:

S = A XOR B
Co = A AND B

Ein Volladdierer beruecksichtigt zusaetzlich den Eingangsuebertrag Ci. Er kann aus zwei Halbaddierern und einem OR-Gatter aufgebaut werden. Die Funktionen lauten:

S = A XOR B XOR Ci
Co = (A AND B) OR (Ci AND (A XOR B))

Die Wahrheitstabelle des Volladdierers lautet:

A | B | Ci | S | Co
0 | 0 | 0 | 0 | 0
0 | 0 | 1 | 1 | 0
0 | 1 | 0 | 1 | 0
0 | 1 | 1 | 0 | 1
1 | 0 | 0 | 1 | 0
1 | 0 | 1 | 0 | 1
1 | 1 | 0 | 0 | 1
1 | 1 | 1 | 1 | 1

Bei gleicher Gatterverzoegerung liegt der kritische Pfad des Volladdierers im Uebertragspfad. Fuer die Bildung von Co durchlaeuft ein Signal im unguenstigsten Fall die Folge XOR, AND und OR. Damit ergeben sich fuer den Volladdierer drei Gatterverzoegerungen. Beim Ripple-Carry-Addierer wird der Uebertrag seriell von der niederwertigsten zur hoechstwertigen Stufe weitergereicht. Dadurch waechst die maximale Durchlaufzeit naeherungsweise linear mit der Wortbreite.

4 Versuchsdurchfuehrung

4.1 Entwurf und Simulation

Vor dem Laboraufbau wurde die Addiererschaltung in Digital entworfen. Fuer den Volladdierer wurden die Bausteine 74LS86, 74LS08 und 74LS32 verwendet. Anschliessend wurde der Entwurf schrittweise zu einem 4-Bit-Ripple-Carry-Addierer erweitert. Die Datei corrected_2.dig dokumentiert den simulierten Schaltungsentwurf.

4.2 Aufbau des Volladdierers und Fehlersuche

Zunaechst wurde ein Volladdierer mit XOR-, AND- und OR-Gattern auf dem Steckbrett aufgebaut. Der Arduino legte nacheinander alle acht Eingangskombinationen fuer A, B und Ci an. Die Ausgangszustaende fuer S und Co wurden mit LEDs angezeigt und mit der vorbereiteten Wahrheitstabelle verglichen.

Beim Aufbau trat zunaechst ein Fehler auf. Nach ungefaehr einer Stunde Fehlersuche wurde festgestellt, dass das verwendete OR-IC beschaedigt war. Wie in der Fotodokumentation zu erkennen ist, fehlte an dem IC ein Bein am Eingang 1. Dadurch konnte das betreffende Eingangssignal nicht korrekt in das OR-Gatter eingespeist werden. Der Fehler lag deshalb nicht an der booleschen Funktion des Volladdierers, sondern an einem defekten Bauteil beziehungsweise an einem unterbrochenen IC-Anschluss.

Der Defekt wurde dokumentiert und bei der weiteren Fehlersuche beruecksichtigt. Nach dem Erkennen des Problems wurde die Verdrahtung erneut geprueft und der betroffene OR-Eingang nicht mehr als zuverlaessige Signalverbindung betrachtet. Zusaetzlich wurde im Arduino-Test ein falscher digitaler Zustand korrigiert: Pin 6 wurde von true auf false geaendert. Danach stimmten die beobachteten Ausgangswerte mit der Soll-Wahrheitstabelle ueberein.

Abbildung 1: Aufbau des Volladdierers auf dem Steckbrett.
Abbildung 2: Dokumentierter Fehler beim Aufbau: Das OR-IC war beschaedigt, da ein Bein am Eingang 1 fehlte.

4.3 Erweiterung zum 4-Bit-Ripple-Carry-Addierer

Nach der Funktionspruefung des einzelnen Volladdierers wurde die Schaltung schrittweise zu einem 4-Bit-Ripple-Carry-Addierer erweitert. Der Ausgangsuebertrag einer Stufe wurde jeweils mit dem Eingangsuebertrag der naechsthoeheren Stufe verbunden. Nach jeder Erweiterung wurde die Funktion erneut mit dem Arduino geprueft. Dieses schrittweise Vorgehen war sinnvoll, weil Fehler so direkt einer bestimmten Addierstufe zugeordnet werden konnten.

Die Summenausgaenge wurden als S0 bis S3 betrachtet. Der letzte Uebertrag bildete den Ausgang Co des gesamten 4-Bit-Addierers.

Abbildung 3: Gesamtaufbau des 4-Bit-Ripple-Carry-Addierers mit Messleitungen.

4.4 Messung der Durchlaufzeit

Zur Messung der Durchlaufzeit wurde eine Eingangskombination gewaehlt, bei der ein Uebertrag durch alle vier Volladdierstufen laeuft. Ein geeignetes Beispiel ist:

A = 1111
B = 0000
Ci: 0 -> 1

Durch diesen Wechsel entsteht in der niederwertigsten Stufe ein Uebertrag, der nacheinander durch die folgenden Stufen weitergereicht wird. Ein Oszilloskopkanal erfasste den ausloesenden Eingang, der zweite Kanal den Ausgang am Ende des kritischen Pfades.

5 Ergebnisse und Auswertung

5.1 Funktionspruefung

Nach der Korrektur der Fehler stimmten die Ausgaenge des Volladdierers mit der Wahrheitstabelle ueberein. Auch die schrittweise erweiterte 4-Bit-Schaltung lieferte die erwarteten Binaersummen. Die Vorgehensweise, nach jeder hinzugefuegten Stufe erneut zu pruefen, erwies sich als wichtig, da Verdrahtungsfehler oder defekte Bauteile schneller eingegrenzt werden konnten.

Der Defekt am OR-IC zeigte, dass bei praktischen Logikaufbauten nicht nur die Schaltungslogik, sondern auch die Bauteile selbst kontrolliert werden muessen. Ein fehlendes IC-Bein kann dazu fuehren, dass ein Eingang elektrisch offen bleibt oder gar nicht mit der Schaltung verbunden ist. Dadurch entstehen falsche Ausgangswerte, obwohl der theoretische Schaltungsentwurf korrekt ist.

5.2 Laufzeit und kritischer Pfad

Am Oszilloskop wurde zwischen dem Eingangssprung und der stabilen Reaktion am Ausgang eine Zeitdifferenz von

Delta t = 82,80 ns

gemessen. Der Kehrwert dieser Zeit betraegt:

1 / 82,80 ns = ca. 12,07 MHz

Dieser Wert ist jedoch nur der mathematische Kehrwert der gemessenen Verzögerung. Er darf nicht ohne weitere Timing-Reserven als sichere maximale Taktfrequenz verwendet werden. In einer realen Schaltung muessen zusaetzlich Setup-Zeiten, Hold-Zeiten, Pegelstabilitaet und Stoerabstaende beruecksichtigt werden.

Waehrend der Messung konnten kurze Zwischenzustaende beziehungsweise Glitches auftreten. Diese entstehen, weil Signale verschiedene Gatterpfade mit unterschiedlichen Laufzeiten durchlaufen. Besonders beim Ripple-Carry-Addierer ist dies erwartbar, da der Uebertrag nicht gleichzeitig in allen Stufen ankommt, sondern von Stufe zu Stufe weitergegeben wird.

Die Durchlaufzeit eines Ripple-Carry-Addierers steigt mit zunehmender Wortbreite an. Naeherungsweise gilt:

tpd(n) = t0 + n * tCarry

Dabei beschreibt n die Anzahl der Addierstufen. Wird die Wortbreite erhoeht, verlaengert sich der Carry-Pfad entsprechend. Das ist der wesentliche Nachteil des Ripple-Carry-Prinzips gegenueber schnelleren Architekturen wie dem Carry-Look-Ahead-Addierer.

6 Fehlerbetrachtung

Die wichtigsten Fehlerquellen des Versuchs waren:

1. Defektes OR-IC: Am OR-Gatter fehlte ein Bein am Eingang 1. Dadurch war der Eingang nicht korrekt angeschlossen und die Schaltung konnte zunaechst nicht wie erwartet funktionieren.
2. Verdrahtungsaufwand: Der 4-Bit-Ripple-Carry-Addierer benoetigt viele Leitungen. Dadurch steigt die Wahrscheinlichkeit von Steckfehlern und Kontaktproblemen.
3. Falscher digitaler Testzustand: Im Arduino-Test musste Pin 6 von true auf false korrigiert werden.
4. Steckbrettkontakte: Wackelkontakte oder nicht vollstaendig eingesteckte Leitungen koennen falsche Logikpegel verursachen.
5. Messbelastung: Oszilloskop-Tastkoepfe und Masseleitungen koennen schnelle Signalflanken beeinflussen.
6. Unterschiedliche Gatterlaufzeiten: XOR-, AND- und OR-Gatter besitzen in der Praxis nicht exakt dieselbe Verzögerung. Dadurch koennen kurze Glitches entstehen.
7. Cursorposition am Oszilloskop: Die manuelle Cursorplatzierung begrenzt die Genauigkeit der Laufzeitmessung.

7 Fazit

Der Volladdierer und der daraus aufgebaute 4-Bit-Ripple-Carry-Addierer konnten erfolgreich untersucht werden. Die theoretische Funktion wurde durch die Wahrheitstabelle beschrieben und anschliessend mit dem Arduino ueberprueft. Bei der Durchfuehrung trat zunaechst ein Fehler auf, da das verwendete OR-IC beschaedigt war und ein Bein am Eingang 1 fehlte. Dieser Defekt erklaerte die fehlerhaften Ausgangswerte beim ersten Aufbau und wurde in der Fehlersuche dokumentiert.

Nach der Korrektur der Verdrahtung beziehungsweise der Beruecksichtigung des defekten ICs sowie der Anpassung des Arduino-Testsignals stimmten die Ausgaenge mit den erwarteten Ergebnissen ueberein. Die gemessene Durchlaufzeit von 82,80 ns bestaetigt, dass sich der Uebertrag beim Ripple-Carry-Addierer nacheinander durch die einzelnen Stufen ausbreitet. Deshalb nimmt die maximale Laufzeit mit steigender Wortbreite naeherungsweise linear zu.

Der Versuch zeigt damit sowohl die theoretische Funktionsweise kombinatorischer Addiererschaltungen als auch die Bedeutung systematischer Fehlersuche im praktischen Laboraufbau.
