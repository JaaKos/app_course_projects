

/*
This is a UI file (.ui.qml) that is intended to be edited in Qt Design Studio only.
It is supposed to be strictly declarative and only uses a subset of QML. If you edit
this file manually, you might introduce QML code that is not supported by Qt Design Studio.
Check out https://doc.qt.io/qtcreator/creator-quick-ui-forms.html for details on .ui.qml files.
*/
import QtQuick
import QtQuick.Controls
import Kalenteri

Rectangle {
    id: rectangle
    width: Constants.width
    height: Constants.height

    color: Constants.backgroundColor

    Column {
        id: column
        x: 220
        y: 40
        width: 200
        height: 400
        anchors.left: parent.left
        anchors.right: parent.right
        leftPadding: 75

        Text {
            id: _text
            text: qsTr("Joulukuu")
            anchors.left: parent.left
            anchors.right: parent.right
            font.pixelSize: 18
            horizontalAlignment: Text.AlignHCenter
        }

        Grid {
            id: grid
            width: 400
            height: 400
            columns: 7

            Repeater {
                id: repeater
                opacity: 1
                clip: false
                model: 31

                Rectangle {
                    id: rectangle1
                    width: 70
                    height: 70
                    color: "#808080"
                    scale: 0.5
                    required property int index

                    MouseArea {
                        id: mouseArea
                        anchors.fill: parent

                        Connections {
                            target: mouseArea
                            onClicked: {
                                _text2.text = "Päivä: " + (index + 1)
                                rectangle2.visible = true
                                propertyAnimation.start()
                            }
                        }
                    }

                    Text {
                        id: _text1
                        text: index + 1
                        anchors.fill: parent
                        font.pixelSize: 38
                        horizontalAlignment: Text.AlignHCenter
                        verticalAlignment: Text.AlignVCenter
                    }
                }
            }
        }
    }

    Rectangle {
        id: rectangle2
        x: 143
        y: 73
        width: 358
        height: 325
        visible: false
        color: "#ffffff"

        Text {
            id: _text2
            x: 76
            y: 69
            width: 201
            height: 79
            text: qsTr("Text")
            anchors.left: parent.left
            anchors.right: parent.right
            font.pixelSize: 24
            horizontalAlignment: Text.AlignHCenter
        }

        Button {
            id: button
            x: 129
            y: 255
            height: 40
            visible: true
            text: qsTr("Sulje")
            anchors.left: parent.left
            anchors.right: parent.right
            anchors.leftMargin: 125
            anchors.rightMargin: 126
            z: 1

            Connections {
                target: button
                onClicked: rectangle2.visible = false
            }
        }

        PropertyAnimation {
            id: propertyAnimation
            target: rectangle2
            property: "opacity"
            from: 0
            to: 1
            duration: 250
            running: false
        }

        Rectangle {
            id: rectangle3
            x: -143
            y: -73
            width: 640
            height: 479
            opacity: 0.5
            visible: true
            color: "#000000"
            z: -1

            MouseArea {
                id: mouseArea1
                anchors.fill: parent
                z: -1
                preventStealing: true

                Connections {
                    target: mouseArea1
                    onClicked: rectangle2.visible = false
                }
            }
        }

        MouseArea {
            id: mouseArea2
            anchors.fill: parent
        }
    }
    states: [
        State {
            name: "clicked"
        }
    ]
}
