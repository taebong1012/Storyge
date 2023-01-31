import "./App.css";
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './pages/1_login/Login';
import Intro from './pages/0_intro/Intro';
import Creatediary from "./pages/3_myDiary/Creatediary";

function App() {
  return (
    <div className="App">
      <p>ㅎㅇ</p>

      <BrowserRouter>
          <Routes>
            <Route path="/" element={<Intro />} />
            <Route path="login" element={<Login />} />
            <Route path="createDiary" element={<Creatediary />} />
          </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
