import * as PropTypes from "prop-types";

const ReactHookInput = ({label, id, type, register, validation, error, placeholder, defaultValue}) => {
    return (
        <div>
            <label htmlFor={id} className="form-label">{label}</label>
            <input
                {...register(id, validation)}
                type={type}
                id={id}
                placeholder={placeholder}
                value={defaultValue ? defaultValue : undefined}
                className={`form-input-box ${error && "error"}`}
            />
            {error && <p className="form-error-label">{error.message}</p>}
        </div>
    );
}

ReactHookInput.propTypes = {
    label: PropTypes.string.isRequired,
    id: PropTypes.string.isRequired,
    type: PropTypes.string.isRequired,
    register: PropTypes.any.isRequired,
    validation: PropTypes.any.isRequired,
    error: PropTypes.object,
    placeholder: PropTypes.string.isRequired,
    value: PropTypes.any,
}

export default ReactHookInput;
