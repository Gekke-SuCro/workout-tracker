import PropTypes from "prop-types";

const SelectExercise = ({ exercises, oncClick }) => {
    return (
        <div className="">
            <div>
                <label htmlFor="searchExercises">Search Exercises</label>
                <input
                    type="text"
                    name="searchExercises"
                    id="searchExercises"
                    className="form-input-box"
                />
            </div>
            <div className="flex flex-col gap-2 py-4">
                {exercises.map((ex, index) => (
                    <div key={index} className="flex justify-between border-2 border-neutral-500 rounded p-2">
                        <div>{ex.name}</div>
                        <div>
                            <button type="button" onClick={() => oncClick(ex)}>select</button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

SelectExercise.propTypes = {
    exercises: PropTypes.array.isRequired,
}

export default SelectExercise;