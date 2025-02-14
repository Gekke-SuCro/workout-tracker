import { useAuth } from "../../context/authContext";
import {useNavigate} from "react-router-dom";

const HomePage = () => {
  const { logout } = useAuth();
  const navigate = useNavigate();

  return (
      <div className="flex flex-col grow justify-center items-center min-h-screen">
          <h1>Workout Tracker</h1>
          <button onClick={() => navigate("/create-workout")} className="px-6 py-2 bg-orange-500 rounded-xl">
              Create Workout
          </button>

          <div className="py-8">
              <button onClick={logout} className="px-6 py-2 bg-red-500 rounded-xl">
                  Logout
              </button>
          </div>
      </div>
  );
};

export default HomePage;
