
#define relay1 7
#define relay2 6
#define relay3 5
#define relay4 4

#define trigPin 10
#define echoPin 13

#define buzzer 9

#define lights A0
#define tailLights A1

#define  signalingRight A4
#define  signalingLeft A3

bool tailLights_state = 1;

int signalingRight_state = -1;
int signalingLeft_state = -1;

float duration;
float cm = 2000; 

char command;

int photocellReading;

int x;

bool mode = true;
bool V_forward = false;
bool V_back = false;
bool V_right = false;
bool V_left = false;

void setup() {
  
  Serial.begin(9600); 
  Serial.setTimeout(30);
  pinMode(relay1, OUTPUT);
  pinMode(relay2, OUTPUT);
  pinMode(relay3, OUTPUT);
  pinMode(relay4, OUTPUT);
  pinMode(trigPin,OUTPUT);
  pinMode(echoPin,INPUT);
  pinMode(buzzer, OUTPUT);
  pinMode(lights, OUTPUT);
  pinMode(tailLights, OUTPUT);
  pinMode(buzzer,OUTPUT);
  pinMode(signalingLeft,OUTPUT);
  pinMode(signalingRight,OUTPUT);
}

void loop() {
  if(Serial.available() > 0){ 
    command = Serial.read();
     switch(command){
      case 'P':  
      sstop();
      V_forward = false;
      V_back = false;
      break;
     case 'M':  
      mode = true;
      sstop();
      V_forward = false;
      V_back = false;
     break;
     case 'N':  
      mode = false;
      sstop();
      V_forward = false;
      V_back = false;
      break;
    case 'W':  
      if(mode) forward();
      else 
      {
        V_forward = true;
        V_back = false;
      }   
      tailLights_state = 0;
      break;
    case 'S':  
       if(mode) back();
       else 
       {
        V_back = true;
        V_forward = false;
       }
       tailLights_state = 0;
      break;
    case 'A':
      if(mode) left();
      else 
      {
        V_left = true;
      }
      tailLights_state = 0;
      break;
    case 'D':
      if(mode) right();
      else 
      {
        V_right = true;
      }
      tailLights_state = 0;
      break; 
      case 'B':
      if(mode) sstop();
      else
      {
        V_right = false;
        V_left = false;
      }
      tailLights_state = 1;
      break;
      case 'X':
      signalingRight_state = signalingRight_state * -1;
      break; 
      case 'Z':
      signalingLeft_state = signalingLeft_state * -1;
      break;  
    }

    if(command == 'H') tone(buzzer,450);
    if(command == 'J') noTone(buzzer);
  }

  if(!mode)
  {
    if(V_forward && V_left == 0 && V_right==0) forward();
    if(V_back && V_left == 0 && V_right==0) back();
    if(V_left) 
    {
      digitalWrite(relay1,LOW);
      digitalWrite(relay3,LOW);
    }
    if(V_right) 
    {
      digitalWrite(relay4,LOW);
      digitalWrite(relay2,LOW);
    }
  }

  ultrasonic();

  if(command == 'W'|| !mode && V_forward) 
  {
    if(cm <= 15) 
    {
      sstop();
      V_forward = false;
      tailLights_state = 1;
    }
  }

  if(tailLights_state == 1) analogWrite(tailLights, 255);
  else  analogWrite(tailLights, 0);
  
  photocellReading = analogRead(A2);

  //Serial.print("Analog reading = ");
  //Serial.println(photocellReading);

  if(photocellReading <500)  analogWrite(lights, 255);
  else  analogWrite(lights, 0);

  if(signalingRight_state == 1) analogWrite(signalingRight,x);
  else analogWrite(signalingRight,0);
  if(signalingLeft_state == 1) analogWrite(signalingLeft,x);
  else analogWrite(signalingLeft,0);
  x+=5;
  if(x==255) x=0;
}
 
void ultrasonic()
{
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(5);
  digitalWrite(trigPin, LOW);
  duration = pulseIn(echoPin, HIGH); 
  cm = microsecondsToCentimeters(duration);
  if(cm<=200)
  {
    Serial.println(cm);
    Serial.print("        ");
  }else
  {
    Serial.println("Out of range      ");
  }
}

float microsecondsToCentimeters(float microseconds) {
  // The speed of sound is 340 m/s or 29 microseconds per centimeter.
  // The ping travels out and back, so to find the distance of the object we
  // take half of the distance travelled.
  return microseconds / 29 / 2;
}

void forward()
{
    digitalWrite(relay1,LOW);
    digitalWrite(relay4,LOW);
    digitalWrite(relay2,HIGH);
    digitalWrite(relay3,HIGH);
}

void back()
{
    digitalWrite(relay1,HIGH);
    digitalWrite(relay4,HIGH);
    digitalWrite(relay2,LOW);
    digitalWrite(relay3,LOW);
}

void left()
{
    digitalWrite(relay1,LOW);
    digitalWrite(relay4,HIGH);
    digitalWrite(relay2,HIGH);
    digitalWrite(relay3,LOW);
}

void right()
{
    digitalWrite(relay1,HIGH);
    digitalWrite(relay4,LOW);
    digitalWrite(relay2,LOW);
    digitalWrite(relay3,HIGH);
}

void sstop()
{
    digitalWrite(relay1,LOW);
    digitalWrite(relay3,LOW);
    digitalWrite(relay2,LOW);
    digitalWrite(relay4,LOW);
}
