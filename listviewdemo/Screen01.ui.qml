

/*
This is a UI file (.ui.qml) that is intended to be edited in Qt Design Studio only.
It is supposed to be strictly declarative and only uses a subset of QML. If you edit
this file manually, you might introduce QML code that is not supported by Qt Design Studio.
Check out https://doc.qt.io/qtcreator/creator-quick-ui-forms.html for details on .ui.qml files.
*/
import QtQuick
import QtQuick.Controls
import Listviewdemo

Rectangle {
    id: rectangle
    width: Constants.width
    height: Constants.height

    color: Constants.backgroundColor

    ListModel {
        id: myModel
    }

    Column {
        id: column
        x: 215
        y: 54
        width: 186
        height: 340
        leftPadding: 15

        Row {
            id: row
            width: 180
            height: 32
            leftPadding: -9

            TextInput {
                id: textInput
                width: 124
                height: 41
                font.pixelSize: 20
                topPadding: 2
                leftPadding: 10

                Rectangle {
                    id: rectangle2
                    color: "#ffffff"
                    border.width: 2
                    anchors.fill: parent
                    anchors.leftMargin: 0
                    anchors.rightMargin: 0
                    anchors.topMargin: 0
                    anchors.bottomMargin: 8
                    z: -1
                }
            }

            Button {
                id: button
                width: 57
                height: 33
                text: qsTr("Lisää")
                highlighted: true

                Connections {
                    target: button
                    onClicked: myModel.append({
                                                  "entry": textInput.text
                                              })
                }
            }
        }

        ListView {
            id: listView
            width: 160
            height: 308
            model: myModel
            delegate: Text {
                text: entry
                font.pixelSize: 16
                padding: 5
            }

            Rectangle {
                id: rectangle1
                color: "#cfcfcf"
                border.width: 2
                anchors.fill: parent
                z: -1
            }
        }

        Text {
            id: _text
            width: 76
            height: 25
            text: "count: " + myModel.count
            anchors.left: parent.left
            anchors.right: parent.right
            font.pixelSize: 18
            horizontalAlignment: Text.AlignHCenter
            leftPadding: 0
        }
    }

    states: [
        State {
            name: "clicked"
        }
    ]
}
