

/*
This is a UI file (.ui.qml) that is intended to be edited in Qt Design Studio only.
It is supposed to be strictly declarative and only uses a subset of QML. If you edit
this file manually, you might introduce QML code that is not supported by Qt Design Studio.
Check out https://doc.qt.io/qtcreator/creator-quick-ui-forms.html for details on .ui.qml files.
*/
import QtQuick
import QtQuick.Controls
import Statedemo

Rectangle {
    id: rectangle
    width: Constants.width
    height: Constants.height

    color: Constants.backgroundColor

    property bool machineOn

    states: [
        State {
            name: "Off"
            when: !rectangle.machineOn
            PropertyChanges {
                target: rectangle1
                scale: 1.05
                color.hslLightness: 0.5
            }
        },
        State {
            name: "On"
            when: rectangle.machineOn
            PropertyChanges {
                target: rectangle2
                scale: 1.05
                color.hslLightness: 0.5
            }
        }
    ]

    Rectangle {
        id: rectangle1
        x: 135
        y: 178
        width: 128
        height: 124
        color: "#890000"

        MouseArea {
            id: mouseArea
            anchors.fill: parent

            Connections {
                target: mouseArea
                onClicked: rectangle.machineOn = false
            }
        }
    }

    Rectangle {
        id: rectangle2
        x: 374
        y: 178
        width: 132
        height: 124
        color: "#018900"

        MouseArea {
            id: mouseArea1
            anchors.fill: parent

            Connections {
                target: mouseArea1
                onClicked: rectangle.machineOn = true
            }
        }
    }

    Button {
        id: button
        x: 270
        y: 373
        text: qsTr("On/Off")
        highlighted: false

        Connections {
            target: button
            onClicked: rectangle.machineOn = !rectangle.machineOn
        }
    }
}
