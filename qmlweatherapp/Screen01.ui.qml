

/*
This is a UI file (.ui.qml) that is intended to be edited in Qt Design Studio only.
It is supposed to be strictly declarative and only uses a subset of QML. If you edit
this file manually, you might introduce QML code that is not supported by Qt Design Studio.
Check out https://doc.qt.io/qtcreator/creator-quick-ui-forms.html for details on .ui.qml files.
*/
import QtQuick
import QtQuick.Controls
import WeatherApp

Rectangle {
    id: rectangle
    width: Constants.width
    height: Constants.height
    gradient: Gradient {
        GradientStop {
            position: 0
            color: "#00b8ff"
        }

        GradientStop {
            position: 1
            color: "#0010f0"
        }
        orientation: Gradient.Vertical
    }

    Column {
        id: column
        x: 233
        y: 147
        width: 200
        height: 145
        spacing: 10

        Row {
            id: row
            width: 200
            height: 32
            leftPadding: 25
            spacing: 10
            clip: false

            Text {
                id: _text
                x: 0
                width: 95
                height: 32
                text: qsTr("Tampere")
                font.pixelSize: 24
                horizontalAlignment: Text.AlignHCenter
                verticalAlignment: Text.AlignTop

                MouseArea {
                    id: mouseArea
                    anchors.fill: parent

                    Connections {
                        target: mouseArea
                        onClicked: _text.color = "yellow"
                    }
                }
            }

            Text {
                id: _text1
                x: 0
                text: qsTr("5°C")
                font.pixelSize: 24
                horizontalAlignment: Text.AlignHCenter
                verticalAlignment: Text.AlignTop
            }
        }

        Image {
            id: image
            width: 100
            height: 100
            visible: true
            anchors.left: parent.left
            anchors.right: parent.right
            horizontalAlignment: Image.AlignHCenter
            verticalAlignment: Image.AlignVCenter
            source: "images/cloudy.png"
            scale: 0.5
            fillMode: Image.PreserveAspectFit
        }
    }

    Button {
        id: button
        x: 286
        y: 373
        text: qsTr("Refresh")

        Connections {
            target: button
            onClicked: {
                const apiKey = ''
                let xhr = new XMLHttpRequest()
                xhr.open("GET",
                         `https://api.openweathermap.org/data/2.5/weather?q=${_text.text}&appid=${apiKey}&units=metric`)
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                            const weatherData = JSON.parse(xhr.responseText)
                            _text1.text = weatherData.main.temp + "°C"
                            const weatherIconCode = weatherData.weather[0].icon
                            let iconUrl = `http://openweathermap.org/img/wn/${weatherIconCode}@2x.png`
                            image.source = iconUrl
                        } else {
                            console.log("HTTP Error:", xhr.status)
                        }
                    }
                }

                xhr.send()
            }
        }
    }

    states: [
        State {
            name: "clicked"
        }
    ]
}
