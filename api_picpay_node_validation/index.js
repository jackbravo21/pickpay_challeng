const express       = require("express");
const app           = express();
const port          = 9001;
const cors              = require("cors");

app.use(express.json());

const api = require("./routes");

app.use(cors());
app.use('/api', api);

app.listen(port, () => {
    console.log("\x1b[34m", `Server running on port: ${port}.`);
});