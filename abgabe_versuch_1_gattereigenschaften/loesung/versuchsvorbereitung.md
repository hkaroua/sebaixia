# Versuchsvorbereitung Versuch 1 - Gattereigenschaften

## 4.1 Inverter - Vorbereitung

### 1. Arduino-Programm fuer PWM mit Potentiometer

Das Arduino-Programm befindet sich in:

- `arduino/pwm_potentiometer.ino`
- `wokwi/sketch.ino`

Der Arduino Uno erzeugt das PWM-Signal auf Pin 9. Dafuer wird Timer1 im
Fast-PWM-Modus mit 8 Bit Aufloesung ohne Vorteiler betrieben.

Berechnung der PWM-Frequenz:

```
f_PWM = f_CPU / 256 = 16 MHz / 256 = 62,5 kHz
```

Der Duty-Cycle wird ueber ein Potentiometer an Analog-Eingang A0 eingestellt.
Der gelesene Wert von 0 bis 1023 wird auf einen PWM-Vergleichswert von 0 bis
255 abgebildet. In der Wokwi-Simulation ist eine LED am PWM-Ausgang
angeschlossen, sodass die Helligkeit vom Duty-Cycle abhaengt.

Anschluss:

- Potentiometer: aeussere Anschluesse an 5 V und GND, Schleifer an A0
- LED: Pin 9 ueber Vorwiderstand an LED-Anode, LED-Kathode an GND

### 2. Wokwi-Simulation

Die Simulation ist durch die Dateien im Ordner `wokwi/` vorbereitet:

- `sketch.ino`
- `diagram.json`
- `wokwi_project_link.txt`

Damit kann das Projekt in Wokwi mit Arduino Uno, Potentiometer und LED
nachgebildet und simuliert werden.

### 3. Zeitkonstante fuer Tiefpassfilter 1. Ordnung

Gegeben ist die gewuenschte 3-dB-Grenzfrequenz:

```
f = 8 Hz
```

Fuer einen Tiefpass 1. Ordnung gilt:

```
f_3dB = 1 / (2 * pi * R * C)
```

Damit ergibt sich fuer die Zeitkonstante:

```
R * C = 1 / (2 * pi * f)
R * C = 1 / (2 * pi * 8 Hz)
R * C = 0,0199 s
```

Die benoetigte Zeitkonstante ist daher:

```
tau = R * C = 19,9 ms
```

Wenn im Versuch ein Widerstand von `R = 2 kOhm` verwendet wird, ergibt sich:

```
C = tau / R
C = 0,0199 s / 2000 Ohm
C = 9,95 uF
```

Ein passender praktischer Wert ist daher:

```
C = 10 uF
```

## 4.2 Ringoszillator - Vorbereitung

### 1. Bedingung fuer die Anzahl N der Inverter

Damit ein Ringoszillator schwingen kann, muss die Anzahl der hintereinander
geschalteten Inverter ungerade sein:

```
N = 3, 5, 7, 9, 11, ...
```

Begruendung: Jeder Inverter dreht die Phase um 180 Grad. Bei einer ungeraden
Anzahl von Invertern liegt am Eingang des ersten Inverters nach dem Rueckkoppeln
das invertierte Signal an. Dadurch gibt es keinen stabilen statischen Zustand.
Wegen der endlichen Gatterlaufzeiten laeuft die Pegelaenderung durch die
Inverterkette und es entsteht eine Oszillation.

Bei einer geraden Anzahl von Invertern waere die Rueckkopplung nicht invertierend.
Die Schaltung koennte dann in einem stabilen Zustand bleiben und wuerde nicht
selbststaendig oszillieren.

### 2. Formel fuer die Verlustkapazitaet eines Inverters

Aus der dynamischen Leistungsaufnahme eines CMOS-Gatters:

```
P_dyn = V_CC^2 * C_pd * f
```

Fuer einen Ringoszillator mit `N` Invertern gilt fuer die gesamte dynamische
Leistungsaufnahme:

```
P_ges = N * V_CC^2 * C_pd1 * f_0
```

Gleichzeitig ist:

```
P_ges = V_CC * I_CC
```

Gleichsetzen und nach `C_pd1` aufloesen:

```
V_CC * I_CC = N * V_CC^2 * C_pd1 * f_0
```

```
C_pd1 = I_CC / (N * V_CC * f_0)
```

Falls ein statischer Ruhestrom beruecksichtigt werden soll, kann man statt
`I_CC` den dynamischen Stromanteil verwenden:

```
C_pd1 = (I_CC - I_CC0) / (N * V_CC * f_0)
```

Im Versuch kann der Ruhestrom unbenutzter Inverter laut Aufgabenstellung
vernachlaessigt werden. Daher wird die vereinfachte Formel verwendet:

```
C_pd1 = I_CC / (N * V_CC * f_0)
```
