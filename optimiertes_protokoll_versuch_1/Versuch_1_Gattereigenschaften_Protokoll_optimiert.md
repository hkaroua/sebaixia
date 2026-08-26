# Versuchsprotokoll Versuch 1 - Gattereigenschaften

**Praktikum:** Digitaltechnik  
**Versuch:** 1 - Gattereigenschaften  
**Name:** Precious Adaugo Nnamaka  
**Betreuer:** Prof. Dr.-Ing. Peter Zipf  

## 1 Ziel des Versuchs

Ziel des ersten Versuchs war es, grundlegende Eigenschaften digitaler Logikgatter
messtechnisch zu untersuchen. Im Mittelpunkt standen der 74LS04-Inverter und ein
Ringoszillator. Beim Inverter wurden das Verhalten bei PWM-Ansteuerung, die
Uebertragungskennlinie sowie die daraus ableitbaren Kenngroessen betrachtet. Beim
Ringoszillator wurden die Abhaengigkeit der Schwingfrequenz von der
Versorgungsspannung und die daraus berechnete effektive Verlustleistungskapazitaet
untersucht.

## 2 Verwendete Bauteile und Messgeraete

| Bauteil / Geraet | Verwendung |
| --- | --- |
| Arduino Uno | Erzeugung eines PWM-Signals |
| Potentiometer | Einstellung des Duty-Cycles |
| LED mit 220-Ohm-Vorwiderstand | Funktionskontrolle des PWM-Signals |
| 74LS04 | Inverter und Ringoszillator |
| Operationsverstaerker | aktiver Tiefpass bzw. Spannungsfolger |
| Widerstand 2 kOhm | Tiefpassfilter 1. Ordnung |
| Kondensator 10 uF | Tiefpassfilter 1. Ordnung |
| Steckbrett und Leitungen | Aufbau der Schaltungen |
| Labornetzteil | einstellbare Versorgungsspannung |
| Digitalmultimeter | Messung von Spannungen und Stromaufnahme |
| USB-Logic-Analyzer | Aufnahme schneller Digitalsignale |

## 3 Theoretische Grundlagen

Ein Inverter bildet die logische NOT-Funktion ab. Bei einem niedrigen
Eingangspegel wird der Ausgang auf HIGH gezogen, bei einem hohen Eingangspegel
liegt der Ausgang auf LOW. Die Uebertragungskennlinie `Vo = f(Vi)` beschreibt den
analogen Zusammenhang zwischen Eingangs- und Ausgangsspannung. Aus ihr lassen
sich unter anderem die Ausgangspegel `VoH` und `VoL`, die Umschaltspannung `VS`
sowie Stoersicherheiten ableiten.

Ein Ringoszillator besteht aus einer ungeraden Anzahl hintereinandergeschalteter
Inverter, wobei der Ausgang des letzten Inverters auf den Eingang des ersten
zurueckgefuehrt wird. Da die Rueckkopplung invertierend ist und jedes Gatter eine
endliche Laufzeit besitzt, entsteht eine periodische Schwingung. Mit abnehmender
Versorgungsspannung werden die Schaltvorgaenge langsamer, sodass die
Schwingfrequenz sinkt.

Fuer die Auswertung der Verlustleistungskapazitaet wurde die vereinfachte
Beziehung

```text
Cpd1 = I_CC / (N * V_CC * f0)
```

verwendet. Dabei ist `N` die Anzahl der verwendeten Inverter, `I_CC` die
gemessene Gesamtstromaufnahme, `V_CC` die Versorgungsspannung und `f0` die
Schwingfrequenz. Da im Aufbau drei Inverter verwendet wurden, gilt hier `N = 3`.

## 4 Versuchsdurchfuehrung

### 4.1 PWM-Erzeugung und Untersuchung des Inverters

Das Potentiometer wurde als Spannungsteiler zwischen 5 V und GND angeschlossen.
Der Schleifer war mit dem Analogeingang A0 des Arduino verbunden. Der Arduino
las den Analogwert ein, bildete ihn auf den Wertebereich 0 bis 255 ab und gab das
entsprechende PWM-Signal an Pin 9 aus. Zunaechst wurde die Funktion mit einer
LED und einem 220-Ohm-Vorwiderstand ueberprueft. Durch Drehen des Potentiometers
aenderte sich die Helligkeit der LED, wodurch die Veraenderung des Duty-Cycles
sichtbar wurde.

Anschliessend wurde das PWM-Signal auf den Eingang eines 74LS04-Inverters
gelegt. Eingang und Ausgang wurden mit dem USB-Logic-Analyzer aufgenommen. Bei
der Messung war zu erkennen, dass das Ausgangssignal gegenueber dem Eingang
invertiert ist. Eine Veraenderung des Potentiometers veraenderte die Pulsbreite,
nicht jedoch die grundsaetzliche Inverterfunktion. Die genaue Bestimmung der
Gatterlaufzeit ist mit dem verwendeten Aufbau nur begrenzt moeglich, weil die
Messung durch Abtastrate, Leitungslaengen und die Belastung des Logic-Analyzers
beeinflusst wird.

Im naechsten Schritt wurde das PWM-Signal mit einem aktiven Tiefpass 1. Ordnung
gefiltert. Der Operationsverstaerker wurde als Spannungsfolger bzw. Puffer
verwendet. Dadurch entstand aus dem PWM-Signal eine einstellbare Gleichspannung,
die als Eingangsspannung fuer die Kennlinienmessung des Inverters diente. Die
Ein- und Ausgangsspannungen wurden mit dem Multimeter gemessen.

**Abbildung 1:** PWM-Aufbau mit Arduino Uno, Potentiometer und LED.  
**Abbildung 2:** Aufbau mit Tiefpass und Operationsverstaerker.  
**Abbildung 3:** Zeitlicher Signalverlauf vor und nach dem Inverter mit dem
USB-Logic-Analyzer.

### 4.2 Messung der Uebertragungskennlinie

Die Eingangsspannung wurde zunaechst ueber den gesamten Bereich von 0 V bis
etwa 5 V variiert. Im Umschaltbereich wurde die Spannung feiner veraendert. Die
aus der handschriftlichen Messliste eindeutig lesbaren Werte sind in Tabelle 1
zusammengefasst.

| Eingangsspannung Vi | Ausgangsspannung Vo |
| ---: | ---: |
| 0,0 V | 4,54 V |
| 0,5 V | 4,47 V |
| 1,0 V | 3,82 V |
| 1,5 V | 0,156 V |
| 2,0 V | 0,159 V |
| 2,5 V | ca. 0,150 V |
| 3,0 V | ca. 0,10 V |

**Tabelle 1:** Messwerte der Uebertragungskennlinie des 74LS04-Inverters.

Die Messwerte zeigen den erwarteten invertierenden Verlauf. Bei kleinen
Eingangsspannungen befindet sich der Ausgang im HIGH-Zustand. Zwischen etwa
1,0 V und 1,5 V faellt die Ausgangsspannung sehr steil ab. Oberhalb dieses
Bereichs liegt der Ausgang stabil im LOW-Zustand.

Aus den vorhandenen Messpunkten kann die Umschaltspannung naeherungsweise durch
lineare Interpolation zwischen 1,0 V und 1,5 V bestimmt werden. Dabei ergibt sich
fuer den Schnittpunkt `Vo = Vi`:

```text
VS ca. 1,34 V
```

Damit lassen sich folgende Naeherungswerte angeben:

| Kenngroesse | Wert / Einschaetzung |
| --- | ---: |
| VoH | ca. 4,54 V |
| VoL | ca. 0,15 V |
| VS | ca. 1,34 V |
| ViLmax | aus den Messwerten nicht exakt bestimmbar |
| ViHmin | aus den Messwerten nicht exakt bestimmbar |
| VoLmax | ca. 0,159 V aus den Messwerten |
| VoHmin | ca. 3,82 V aus den Messwerten im Uebergangsbereich |

Die Punkte `ViLmax` und `ViHmin` waeren streng genommen dort zu bestimmen, wo die
Kennlinie die Steigung -1 besitzt. Dafuer waeren im Uebergangsbereich mehr
Messwerte mit kleineren Spannungsschritten erforderlich. Die vorhandene
Messreihe erlaubt daher nur eine qualitative bzw. naeherungsweise Auswertung.

Fuer die typische statische Stoersicherheit ergeben sich mit den gemessenen
Pegelwerten:

```text
VnLstat = VS - VoL = 1,34 V - 0,15 V = 1,19 V
VnHstat = VoH - VS = 4,54 V - 1,34 V = 3,20 V
```

Fuer eine konservative Worst-Case-Betrachtung koennen die typischen
TTL-Grenzwerte des 74LS04 herangezogen werden:

```text
ViLmax = 0,8 V
ViHmin = 2,0 V
VoLmax = 0,5 V
VoHmin = 2,7 V
```

Daraus folgt:

```text
VnL = ViLmax - VoLmax = 0,8 V - 0,5 V = 0,3 V
VnH = VoHmin - ViHmin = 2,7 V - 2,0 V = 0,7 V
```

Die gemessene Kennlinie bestaetigt somit die NOT-Funktion des Inverters. Der
Umschaltbereich liegt deutlich unterhalb der halben Betriebsspannung, was fuer
TTL-Logik typisch ist.

### 4.3 Ringoszillator

Der Ringoszillator wurde abweichend von der Versuchsanleitung nicht mit elf,
sondern mit drei Invertern aufgebaut. Diese Abweichung ist funktional zulaessig,
weil auch drei Inverter eine ungerade Anzahl bilden. Der Ausgang des dritten
Inverters wurde auf den Eingang des ersten Inverters zurueckgefuehrt. Aufgrund
der geringeren Anzahl an Gattern ist gegenueber einem Aufbau mit elf Invertern
eine hoehere Schwingfrequenz zu erwarten.

Die Versorgungsspannung wurde nacheinander auf 5 V, 4 V, 3 V und 2,5 V
eingestellt. Fuer jede Spannung wurden die Gesamtstromaufnahme und die
Schwingfrequenz gemessen. Die Frequenz wurde mit dem USB-Logic-Analyzer
bestimmt.

**Abbildung 4:** Ringoszillator mit drei Invertern und Labornetzteil.

| VCC | ICC | f0 | Pges = VCC * ICC | P je Inverter | Cpd1 |
| ---: | ---: | ---: | ---: | ---: | ---: |
| 5,0 V | 4 mA | 18,60 MHz | 20,0 mW | 6,67 mW | 14,34 pF |
| 4,0 V | 14 mA | 16,00 MHz | 56,0 mW | 18,67 mW | 72,92 pF |
| 3,0 V | 17 mA | 10,41 MHz | 51,0 mW | 17,00 mW | 181,45 pF |
| 2,5 V | 19 mA | 4,90 MHz | 47,5 mW | 15,83 mW | 517,01 pF |

**Tabelle 2:** Verlustleistung und berechnete effektive Verlustleistungskapazitaet
bei `N = 3`.

Die Messwerte zeigen, dass die Schwingfrequenz mit sinkender Versorgungsspannung
deutlich abnimmt. Das entspricht der Erwartung, da die Schaltvorgaenge bei
geringerer Versorgungsspannung langsamer werden. Auffaellig ist, dass die
berechnete Kapazitaet `Cpd1` stark ansteigt. Als reine interne Bauteilkenngroesse
sollte sie jedoch nicht in diesem Ausmass von der Versorgungsspannung abhaengen.

Die starke Streuung der berechneten Werte zeigt daher, dass `Cpd1` hier eher als
effektive Ersatzgroesse zu verstehen ist. Moegliche Ursachen sind:

- Belastung durch den USB-Logic-Analyzer,
- zusaetzliche Kapazitaeten durch Steckbrett und Leitungen,
- Messunsicherheiten bei Strom und Frequenz,
- statische Stromanteile, die in der vereinfachten Formel der dynamischen
  Verlustleistung zugerechnet werden,
- die Verwendung eines realen 74LS04, dessen Verhalten nicht ideal dem
  vereinfachten CMOS-Modell entspricht.

Besonders bei 2,5 V ist die gemessene Frequenz klein. Da `f0` im Nenner der
Formel steht, fuehrt dies zu einem besonders grossen berechneten Wert fuer
`Cpd1`. Aussagekraeftiger waeren wiederholte Messungen, kuerzere Leitungen, eine
moeglichst geringe Messbelastung und das Abziehen eines separat bestimmten
Ruhestroms.

## 5 Fehlerbetrachtung

Die Genauigkeit der Ergebnisse wird durch mehrere praktische Einfluesse begrenzt:

1. Die Kennlinie des Inverters wurde nur mit wenigen eindeutig lesbaren
   Messpunkten dokumentiert. Deshalb lassen sich `ViLmax` und `ViHmin` nicht
   exakt aus der Messkurve bestimmen.
2. Im Umschaltbereich aendert sich die Ausgangsspannung sehr stark. Schon kleine
   Abweichungen der Eingangsspannung fuehren dort zu grossen Aenderungen am
   Ausgang.
3. Der USB-Logic-Analyzer und die Messleitungen belasten schnelle Signale und
   koennen die gemessene Frequenz beeinflussen.
4. Steckbrettkontakte, lange Leitungen und parasitaere Kapazitaeten wirken sich
   besonders beim Ringoszillator aus.
5. Die Berechnung von `Cpd1` beruht auf einem vereinfachten Modell. Nicht alle
   gemessenen Stromanteile sind rein dynamisch.

## 6 Fazit

Der Versuch bestaetigte die grundlegende Funktion des 74LS04-Inverters. Die
gemessene Uebertragungskennlinie zeigt klar den invertierenden Verlauf: Bei
kleiner Eingangsspannung liegt der Ausgang auf HIGH, bei groesserer
Eingangsspannung auf LOW. Der Umschaltbereich befindet sich zwischen etwa 1,0 V
und 1,5 V; die naeherungsweise bestimmte Umschaltspannung betraegt ca. 1,34 V.

Der Ringoszillator funktionierte mit drei Invertern, da die Anzahl der Inverter
ungerade war. Mit sinkender Versorgungsspannung nahm die Schwingfrequenz
deutlich ab. Die berechneten Werte fuer `Cpd1` streuen stark und sind daher nicht
als konstante Bauteilparameter, sondern als durch Messaufbau und Modellannahmen
beeinflusste Ersatzwerte zu interpretieren. Insgesamt zeigen die Messergebnisse
den Zusammenhang zwischen Pegelverhalten, Laufzeit, Versorgungsspannung und
Verlustleistung digitaler Gatter.

## 7 Erklaerung zur Nutzung digitaler Hilfsmittel

Dieses ueberarbeitete Protokoll wurde sprachlich und strukturell mit
KI-Unterstuetzung optimiert. Die Messwerte, Beobachtungen und der durchgefuehrte
Laboraufbau stammen aus der eigenen Versuchsdurchfuehrung. Inhaltliche
Ergaenzungen wurden nur zur klareren Auswertung, zur Fehlerbetrachtung und zur
fachlichen Einordnung der vorhandenen Messdaten vorgenommen.
