import './App.css';
import {Route, Routes} from "react-router-dom";
import LoginPage from "./Pages/LoginPage";
import MainPage from "./Pages/MainPage";
import {TestPage} from "./Pages/TestPage";
import RegisterPage from "./Pages/RegisterPage";

function App() {
    return (
        <div className="App">
            <Routes>
                <Route path="/login" element={<LoginPage />}/>
                <Route path="/test" element={<TestPage/>}/>
                <Route path="/register" element={<RegisterPage/>}/>
                <Route path="/*" element={<MainPage />}/>
            </Routes>
        </div>
    );
}

export default App;
