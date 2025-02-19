

/*
This is a UI file (.ui.qml) that is intended to be edited in Qt Design Studio only.
It is supposed to be strictly declarative and only uses a subset of QML. If you edit
this file manually, you might introduce QML code that is not supported by Qt Design Studio.
Check out https://doc.qt.io/qtcreator/creator-quick-ui-forms.html for details on .ui.qml files.
*/
import QtQuick
import QtQuick.Controls
import Component

Rectangle {
    id: rectangle
    width: Constants.width
    height: Constants.height

    color: Constants.backgroundColor

    Column {
        id: column
        x: 252
        y: 42
        width: 200
        height: 400
        anchors.left: parent.left
        anchors.right: parent.right
        anchors.top: parent.top
        anchors.bottom: parent.bottom
        anchors.leftMargin: 258
        anchors.rightMargin: 253
        anchors.topMargin: 87
        anchors.bottomMargin: 66

        CustomButton {
            id: customButton
            buttonColor: "#fa2626"

            Connections {
                target: customButton
                onClicked: rectangle.color = "red"
            }
        }

        CustomButton {
            id: customButton1
            buttonColor: "#32ea16"

            Connections {
                target: customButton1
                onClicked: rectangle.color = "green"
            }
        }

        CustomButton {
            id: customButton2
            buttonColor: "#398ef6"

            Connections {
                target: customButton2
                onClicked: rectangle.color = "blue"
            }
        }
    }
    states: [
        State {
            name: "clicked"
        }
    ]
}
