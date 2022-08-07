import logo from './logo.svg';
import './App.css';
import {Route, Routes} from "react-router-dom";
import LoginPage from "./Pages/LoginPage";
import IndexPage from "./Pages/IndexPage";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/login" element={<LoginPage/>} />
        <Route path="/index" element={<IndexPage/>}/>
      </Routes>
    </div>
  );
}

export default App;