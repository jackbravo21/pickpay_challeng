const express = require("express");
const router = express.Router();

router.get("/authorize", (req, res) => {
    const check = Math.floor(Math.random() * 5) + 1;

    if (check === 1 || check === 3 || check === 5) {
        return res.json({
            status: "success",
            data: { authorization: true }
        });
    } 
    else {
        return res.status(403).json({
            status: "fail",
            data: { authorization: false }
        });
    }
});

router.post("/notify", (req, res) => {
    const check = Math.floor(Math.random() * 5) + 1;

    console.log("Mensagem Recebida:");
    console.log(req.body);

    if(check === 1 || check === 3 || check === 5) {
        return res.json({
            status: "success",
            data: { message: "Notification sent successfully" }
        });
    } 
    else {
        return res.status(404).json({
            status: "fail",
            data: { message: "Notification service unavailable" }
        });
    }
});


module.exports = router;