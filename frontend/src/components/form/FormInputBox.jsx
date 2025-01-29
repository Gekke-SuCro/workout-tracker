import PropTypes from "prop-types";

const FormInputBox = ({ type, name, id, required = false }) => {
  return (
    <input
      type={type}
      name={name}
      id={id}
      required={required}
      className="p-2 border-2 border-neutral-200 bg-neutral-100 rounded-sm"
    />
  );
};

FormInputBox.propTypes = {
  type: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
  id: PropTypes.string.isRequired,
  required: PropTypes.bool,
};

export default FormInputBox;
