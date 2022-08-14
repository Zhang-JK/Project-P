import logo from './logo.svg';
import './App.css';
import {Route, Routes} from "react-router-dom";
import LoginPage from "./Pages/LoginPage";
// import IndexPage from "./Pages/IndexPage";
import TestPage from "./Pages/TestPage";
import HomePage from "./Pages/HomePage";

function App() {
    console.log("app")
    return (
        <div className="App">
            <Routes>
                <Route path="/home" element={<HomePage/>}/>
                <Route path="/login" element={<LoginPage/>}/>
                {/*<Route path="/index" element={<IndexPage/>}/>*/}
                <Route path="/test" element={<TestPage/>}/>
            </Routes>
        </div>
    );
}

export default App;
