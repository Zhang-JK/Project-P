import './App.css';
import {Route, Routes} from "react-router-dom";
import LoginPage from "./Pages/LoginPage";
import IndexPage from "./Pages/HomePage";
import TemplatePage from "./Pages/TemplatePage";

function App() {
    return (
        <div className="App">
            <Routes>
                <Route path="/" element={<TemplatePage/>}/>
                <Route path="/login" element={<LoginPage/>}/>
                <Route path="/index" element={<IndexPage/>}/>
            </Routes>
        </div>
    );
}

export default App;
