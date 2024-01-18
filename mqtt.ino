// 5. M5STACK
#include <M5Stack.h>
#include <WiFi.h>
#include <PubSubClient.h>

const char* ssid = "_SSID_";
const char* password = "_PASSWORD_";
const char* mqtt_server = "broker.hivemq.com";
const char* mqtt_topic = "examen/_ApellidoInicial_";

WiFiClient espClient;
PubSubClient client(espClient);

void callback(char* topic, byte* payload, unsigned int length) {
    Serial.print("Mensaje recibido en [");
    Serial.print(topic);
    Serial.print("] ");
    String message;
    for (int i = 0; i < length; i++) {
        message += (char)payload[i];
    }
    Serial.println(message);
    M5.Lcd.clear();
    M5.Lcd.setCursor(0, 0);
    M5.Lcd.print("Valor: ");
    M5.Lcd.print(message);
}

void setup() {
    M5.begin();
    M5.Power.begin();
    Serial.begin(115200);
    // Conexión a WiFi
    WiFi.begin(ssid, password);
    while (WiFi.status() != WL_CONNECTED) {
        delay(1000);
        Serial.println("Conectando a WiFi...");
    }
    Serial.println("Conectado a WiFi");
    client.setServer(mqtt_server, 1883);
    client.setCallback(callback);
    // Se conecta al servidor MQTT
    connectMQTT();
}

void loop() {
    if (!client.connected()) {
        reconnectMQTT();
    }
    client.loop();
    delay(100);
}

void connectMQTT() {
    // Mientras no estemos conectados al servidor MQTT...
    while (!client.connected()) {
        Serial.println("Conectando al servidor MQTT...");
        // Intenta conectar al servidor MQTT
        if (client.connect("M5StackClient")) {
            Serial.println("Conectado al servidor MQTT");
            // Suscribirse al tema una vez conectado
            client.subscribe(mqtt_topic);
        } else {
            // Si no se pudo conectar, imprime el estado y espera 5 segundos antes de intentar nuevamente
            Serial.print("Error de conexión, rc=");
            Serial.print(client.state());
            Serial.println(". Intentando de nuevo en 5 segundos");
            delay(5000);
        }
    }
}