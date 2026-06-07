/*
  Versuch 1 - Gattereigenschaften
  PWM-Erzeugung mit Arduino Uno und Potentiometer

  PWM-Ausgang: Pin 9 (OC1A)
  Potentiometer: A0

  Timer1 wird im Fast-PWM-Modus mit 8 Bit Aufloesung ohne Vorteiler betrieben:
  f_PWM = 16 MHz / 256 = 62,5 kHz
*/

const byte POT_PIN = A0;
const byte PWM_PIN = 9;

void setup() {
  pinMode(POT_PIN, INPUT);
  pinMode(PWM_PIN, OUTPUT);

  // Timer1: Fast PWM 8 Bit, nicht-invertierender Ausgang auf OC1A/Pin 9.
  TCCR1A = _BV(COM1A1) | _BV(WGM10);
  TCCR1B = _BV(WGM12) | _BV(CS10);

  OCR1A = 0;
}

void loop() {
  int potValue = analogRead(POT_PIN);
  byte dutyCycle = map(potValue, 0, 1023, 0, 255);

  OCR1A = dutyCycle;

  delay(5);
}
