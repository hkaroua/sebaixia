Versuchsprotokoll Versuch 3 - Sequentielle Logik

Praktikum: Digitaltechnik
Versuch: 3 - Sequentielle Logik
Name: Precious Adaugo Nnamaka
Betreuer: Prof. Dr.-Ing. Peter Zipf

1 Ziel des Versuchs

Ziel des dritten Versuchs war die Untersuchung sequentieller Logikschaltungen. Im Gegensatz zu rein kombinatorischen Schaltungen haengt der Ausgang einer sequentiellen Schaltung nicht nur von den aktuellen Eingangswerten ab, sondern auch vom gespeicherten Zustand. Im Versuch wurden daher zunaechst verschiedene Speicherelemente aufgebaut und verglichen: RS-Latch, D-Latch, D-Flipflop und T-Flipflop. Anschliessend wurde ein synchroner mod-5-Zaehler mit drei D-Flipflops realisiert, mit einem Arduino getaktet und anhand seiner Zustandsfolge ueberprueft.

2 Verwendete Bauteile und Dateien

Bauteil / Datei | Verwendung
Arduino Uno | Takterzeugung fuer den Zaehler
Steckbrett und Leitungen | Aufbau der Schaltungen
74xx00 / NAND-Gatter | RS-Latch, D-Latch und D-Flipflop
74xx04 / NOT-Gatter | Invertierung von Signalen
74xx74 / D-Flipflop | Zaehlerregister
74xx08 / AND-Gatter | Uebergangslogik des Zaehlers
74xx32 / OR-Gatter | Uebergangslogik des Zaehlers
LEDs mit Vorwiderstaenden | Anzeige der Ausgangszustaende
Versuch3.dig | Simulation der Speicherelemente
Versuch3_5mod_Zaehler.dig | Simulation des mod-5-Zaehlers
Arduino-Sketch | Taktsignal an Digitalpin 12

3 Theoretische Grundlagen

Sequentielle Schaltungen besitzen ein Gedaechtnis. Der aktuelle Ausgang kann deshalb vom aktuellen Eingang und vom vorher gespeicherten Zustand abhaengen. Ein einfaches Speicherelement ist das RS-Latch. Es kann ein Bit speichern und besitzt die Funktionen Setzen, Ruecksetzen und Speichern.

Beim NAND-RS-Latch sind die Eingaenge aktiv-low. Daher werden sie als S_bar und R_bar bezeichnet. Ein Low-Pegel an S_bar setzt den Ausgang Q auf 1. Ein Low-Pegel an R_bar setzt Q auf 0. Sind beide Eingaenge high, bleibt der vorherige Zustand gespeichert. Die Kombination S_bar = 0 und R_bar = 0 ist verboten, weil beide Ausgaenge gleichzeitig aktiv werden koennen und nach dem Loslassen kein eindeutig definierter Folgezustand garantiert ist.

Das D-Latch erweitert das RS-Latch um einen Dateneingang D und einen Takteingang beziehungsweise Enable-Eingang CLK. Bei CLK = 1 ist das Latch transparent, also folgt Q direkt dem Eingang D. Bei CLK = 0 bleibt der zuletzt uebernommene Wert gespeichert.

Das D-Flipflop ist flankengesteuert. Der Ausgang Q uebernimmt den Wert von D nur bei der aktiven Taktflanke, hier bei der steigenden Flanke. Zwischen zwei Taktflanken bleibt Q unveraendert. Dadurch ist das D-Flipflop robuster als ein transparentes D-Latch.

Ein T-Flipflop kann aus einem D-Flipflop aufgebaut werden, indem der invertierte Ausgang auf den D-Eingang zurueckgefuehrt wird. Dadurch wechselt Q bei jeder aktiven Taktflanke seinen Zustand. Das Ausgangssignal besitzt dadurch die halbe Frequenz des Eingangstaktes.

4 Durchfuehrung und Ergebnisse

4.1 RS-Latch

Das RS-Latch wurde aus zwei rueckgekoppelten NAND-Gattern auf dem Steckbrett aufgebaut. Zwei Taster dienten als Eingaben fuer S_bar und R_bar. Pull-Widerstaende verhinderten undefinierte Floating-Pegel. Die Ausgaenge Q und Q_bar wurden mit zwei LEDs angezeigt, jeweils mit Vorwiderstand.

Die Funktionspruefung ergab:

S_bar | R_bar | Q+ | Funktion
1 | 1 | Q | Speichern
0 | 1 | 1 | Setzen
1 | 0 | 0 | Ruecksetzen
0 | 0 | nicht definiert | verboten

Die LEDs bestaetigten die Speicherwirkung. Bei S_bar = R_bar = 1 blieb der zuletzt gespeicherte Zustand erhalten. Ein Low-Pegel an S_bar setzte Q auf 1, ein Low-Pegel an R_bar setzte Q auf 0. Im zulaessigen Betrieb waren Q und Q_bar komplementaer.

4.2 D-Latch

Anschliessend wurde die Schaltung zu einem D-Latch erweitert. Die beiden Taster wurden nun als Dateneingang D und Takteingang CLK verwendet. Das D-Latch beseitigt die verbotene Eingangskombination des RS-Latches, da intern immer nur ein definierter Setz- oder Ruecksetzzustand erzeugt wird.

Die Uebergangstabelle lautet:

CLK | D | Q+ | Bedeutung
0 | X | Q | Speichern
1 | 0 | 0 | D = 0 wird uebernommen
1 | 1 | 1 | D = 1 wird uebernommen

Bei CLK = 1 war das Latch transparent. Der Ausgang Q folgte also direkt dem Dateneingang D. Bei CLK = 0 blieb der zuvor uebernommene Wert gespeichert, auch wenn sich D danach aenderte. Das Timing-Diagramm zeigt daher, dass Q nur waehrend des aktiven Taktpegels dem Eingang D folgt.

4.3 D-Flipflop

Im naechsten Schritt wurde die Schaltung zu einem positiv flankengesteuerten D-Flipflop erweitert. Anders als beim D-Latch wird der Eingangswert hier nicht waehrend des gesamten HIGH-Pegels uebernommen, sondern nur an der steigenden Taktflanke.

Die Uebergangstabelle lautet:

Taktbedingung | D | Q+ | Bedeutung
keine steigende Flanke | X | Q | Speichern
steigende CLK-Flanke | 0 | 0 | D = 0 uebernehmen
steigende CLK-Flanke | 1 | 1 | D = 1 uebernehmen

Im Timing-Diagramm wurde sichtbar, dass Q nur an der steigenden Taktflanke den unmittelbar davor anliegenden D-Wert uebernimmt. Zwischen den Flanken bleibt Q konstant. Q_temp beschreibt dabei den Ausgang der internen Master-Stufe. Diese nimmt den Datenwert in der entgegengesetzten Taktphase auf und gibt ihn bei der aktiven Flanke an die Slave-Stufe weiter.

4.4 T-Flipflop

Das T-Flipflop wurde durch Rueckfuehrung des invertierten Ausgangs Q_bar auf den D-Eingang realisiert. Dadurch wird bei jeder steigenden Taktflanke der jeweils entgegengesetzte Zustand uebernommen.

Die Uebergangstabelle lautet:

Taktbedingung | Q | Q+ | Funktion
keine steigende Flanke | Q | Q | Speichern
steigende CLK-Flanke | 0 | 1 | Umschalten
steigende CLK-Flanke | 1 | 0 | Umschalten

Die Messung bestaetigte, dass Q bei jeder steigenden Taktflanke wechselt. Dadurch gilt fuer die Ausgangsfrequenz:

f_Q = f_CLK / 2

Das T-Flipflop wirkt damit als Frequenzteiler durch zwei.

4.5 Synchroner mod-5-Zaehler

Im zweiten Versuchsteil wurde ein synchroner mod-5-Zaehler mit drei D-Flipflops aufgebaut. Alle Flipflops erhielten denselben Takt. Dadurch wechselten alle Registerzustaende synchron zur gleichen Taktflanke. Der Arduino-Ausgang D12 lieferte das Taktsignal.

Die Sollfolge des Zaehlers lautet:

000 -> 001 -> 010 -> 011 -> 100 -> 000

Die Zustaende 101, 110 und 111 gehoeren nicht zum regulaeren Zaehlzyklus.

Die Zustandsuebergangstabelle lautet:

Dezimal | Q2 Q1 Q0 | Q2+ Q1+ Q0+
0 | 000 | 001
1 | 001 | 010
2 | 010 | 011
3 | 011 | 100
4 | 100 | 000

Aus den KV-Diagrammen der Vorbereitung ergaben sich folgende minimierte Eingangsfunktionen:

D2 = Q1 AND Q0
D1 = (NOT Q1 AND Q0) OR (Q1 AND NOT Q0)
D0 = NOT Q2 AND NOT Q0

Fuer die Realisierung wurden zwei 74xx74-ICs fuer drei D-Flipflops, ein 74xx08 fuer AND-Verknuepfungen und ein 74xx32 fuer die OR-Verknuepfung benoetigt. Die invertierten Ausgaenge der 74xx74 konnten genutzt werden, sodass keine zusaetzlichen NOT-Gatter fuer diese Signale erforderlich waren.

Die nicht belegten Zustaende fuehren mit den verwendeten Gleichungen wieder in den regulaeren Bereich:

Zustand 101 -> 010
Zustand 110 -> 010
Zustand 111 -> 100

Damit ist die Schaltung selbstkorrigierend, da sie nach einem ungueltigen Zustand wieder in einen gueltigen Zustand des mod-5-Zyklus zurueckkehrt.

4.6 Arduino-Takt und Timing-Nachweis

Der Arduino-Ausgang D12 wurde als gemeinsamer Takteingang verwendet. Der Sketch setzte den Ausgang jeweils 300 ms auf HIGH und 300 ms auf LOW. Damit ergaben sich:

HIGH-Zeit = 300 ms
LOW-Zeit = 300 ms
Periodendauer T = 600 ms
Frequenz f = 1 / 0,6 s = ca. 1,67 Hz

Die langsame Frequenz war geeignet, um die Zustaende an den LEDs eindeutig zu beobachten. Fuer die Kontrolle wurden CLK sowie Q0, Q1 und Q2 betrachtet. Nach jeder steigenden Taktflanke ergab sich die Folge:

000, 001, 010, 011, 100, 000

In der wiederholten Sequenz traten keine zusaetzlichen Zustaende, Aussetzer oder fehlerhaften Spruenge auf. Damit wurde die korrekte Funktion des synchronen mod-5-Zaehlers bestaetigt.

5 Mess- und Fotodokumentation

Die ergaenzten Timing-Diagramme bestaetigten das beschriebene Speicherverhalten. Beim RS-Latch waren Setzen, Ruecksetzen und Speichern sichtbar. Beim D-Latch folgte Q dem Eingang D nur waehrend des aktiven Taktpegels. Beim D-Flipflop wurde D nur an der steigenden Taktflanke uebernommen. Beim T-Flipflop wechselte Q mit jeder steigenden Taktflanke.

Die Fotodokumentation zeigte ausserdem den real aufgebauten mod-5-Zaehler. Die beobachtete Zustandsfolge entsprach der Sollfolge 0 -> 1 -> 2 -> 3 -> 4 -> 0. Dadurch wurde die Rueckkehr in den Anfangszustand nach Zustand 4 bestaetigt.

6 Auswertung

Der Versuch zeigt den Unterschied zwischen kombinatorischer und sequentieller Logik. Beim RS-Latch entsteht die Speicherwirkung durch Rueckkopplung. Das D-Latch erweitert dieses Prinzip um einen Dateneingang und einen Takteingang, bleibt aber waehrend des aktiven Taktpegels transparent. Das D-Flipflop reduziert diese Transparenz auf eine Taktflanke und eignet sich deshalb besser als Registerelement in synchronen Schaltungen.

Das T-Flipflop zeigte anschaulich, wie durch Rueckkopplung ein toggelndes Verhalten entsteht. Da der Zustand bei jeder Taktflanke wechselt, halbiert sich die Frequenz am Ausgang.

Der mod-5-Zaehler verband drei D-Flipflops mit einer kombinatorischen Uebergangslogik. Da alle Flipflops denselben Takt erhielten, arbeitete die Schaltung synchron. Die beobachtete Folge 000 -> 001 -> 010 -> 011 -> 100 -> 000 bestaetigte die korrekte Funktion. Die Betrachtung der ungenutzten Zustaende zeigte ausserdem, dass die Schaltung nach einem Fehlerzustand wieder in den regulaeren Zaehlzyklus zurueckfinden kann.

7 Fehlerbetrachtung

Moegliche Fehlerquellen bei diesem Versuch waren:

1. Floating-Pegel an Tastereingaengen, falls Pull-Up- oder Pull-Down-Widerstaende fehlen.
2. Vertauschte aktive Pegel beim NAND-RS-Latch, da die Eingaenge aktiv-low arbeiten.
3. Kontaktprobleme auf dem Steckbrett oder lose Jumper-Leitungen.
4. Fehlende Vorwiderstaende an LEDs, die Bauteile beschaedigen koennten.
5. Falsche Taktflanke bei der Interpretation des D-Flipflops.
6. Nicht beschaltete Eingaenge der D-Flipflops, die zu undefinierten Zustaenden fuehren koennen.
7. Fehler in der Uebergangslogik des mod-5-Zaehlers, die zu falschen oder nicht selbstkorrigierenden Zustaenden fuehren wuerden.

8 Fazit

Die untersuchten Speicherelemente konnten erfolgreich aufgebaut und in ihrer Funktion verglichen werden. Das RS-Latch zeigte die grundlegende Speicherwirkung durch Rueckkopplung, besitzt aber eine verbotene Eingangskombination. Das D-Latch loest dieses Problem, ist jedoch waehrend des aktiven Taktpegels transparent. Das D-Flipflop uebernimmt Daten nur an der steigenden Taktflanke und eignet sich dadurch als stabiles Registerelement. Das T-Flipflop zeigte das Umschalten bei jeder Taktflanke und damit die Frequenzteilung durch zwei.

Der synchrone mod-5-Zaehler durchlief die erwartete Folge 000 -> 001 -> 010 -> 011 -> 100 -> 000. Die gemeinsame Taktung aller Flipflops sorgte fuer geordnete Zustandswechsel. Insgesamt verdeutlichte der Versuch den Uebergang von einfachen Speicherelementen zu synchronen Schaltwerken.
