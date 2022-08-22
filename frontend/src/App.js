import './App.css';
import {Route, Routes} from "react-router-dom";
import LoginPage from "./Pages/LoginPage";
import HomePage from "./Pages/HomePage";
import FeedbackPage from "./Pages/FeedbackPage";
import {TestPage} from "./Pages/TestPage";
import UserManagePage from "./Pages/UserManagePage";
import RegisterPage from "./Pages/RegisterPage";

function App() {
    return (
        <div className="App">
            <Routes>
                <Route path="/" element={<HomePage />}/>
                <Route path="/home" element={<HomePage />}/>
                <Route path="/member" element={<UserManagePage />}/>
                <Route path="/login" element={<LoginPage />}/>
                <Route path="/test" element={<TestPage/>}/>
                <Route path="/feedback/*" element={<FeedbackPage/>}/>
                <Route path="/register" element={<RegisterPage/>}/>
            </Routes>
        </div>
    );
}

export default App;
