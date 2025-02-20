import QtQuick

Canvas {
    id: myCanvas
    width: 300
    height: 200
    property real ballSize: 20
    property color ballColor: "green"

    ListModel {
        id: ballModel
    }

    onPaint: {
        var ctx = getContext("2d");
        ctx.clearRect(0, 0, width, height);

        for (let i = 0; i < ballModel.count; i++) {
            let ball = ballModel.get(i);
            ctx.fillStyle = ball.ballColor;
            ctx.beginPath();
            ctx.arc(ball.ballX, ball.ballY, ball.ballSize, 0, 2 * Math.PI);
            ctx.fill();
        }
    }

    function getRandomColor(){
        let hue = parseInt(Math.random() * 360)
        let saturation = 100
        let lightness = 50
        return `hsl(${hue}, ${saturation}%, ${lightness}%)`
    }

    MouseArea {
        anchors.fill: parent
        onClicked: (mouse) => {
            ballModel.append({
                ballX: mouse.x,
                ballY: mouse.y,
                ballColor: getRandomColor(),
                ballSize: 20,
                speedX: (Math.random() - 0.5) * 10,
                speedY: (Math.random() - 0.5) * 10
            })
            requestPaint();
            updateTimer.start();
        }
    }

    Timer {
        id: updateTimer
        interval: 10
        running: false
        repeat: true
        onTriggered: {
            for (let i = 0; i < ballModel.count; i++) {
                let ball = ballModel.get(i);

                while (ball.ballX + ball.speedX < 20 || ball.ballX + ball.speedX > 280) {
                    ball.speedX = (Math.random() - 0.5) * 10;
                }
                while (ball.ballY + ball.speedY < 20 || ball.ballY + ball.speedY > 180) {
                    ball.speedY = (Math.random() - 0.5) * 10;
                }

                ball.ballX += ball.speedX;
                ball.ballY += ball.speedY;
            }

            myCanvas.requestPaint();
        }
    }
}
