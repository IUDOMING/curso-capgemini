function Calculator(calcDisplay) {
	if (calcDisplay && typeof (calcDisplay) !== 'function')
		throw new Error('Missing function for printing')
	let ref = this;
	let all = 0;
	let operator = '+';
	let clean = true;
	let mDisplay = '0'


	ref.fDisplay = '0';


	ref.onDisplayChange = calcDisplay;

	
	function printDisplay() {
		ref.fDisplay = mDisplay;
		if (typeof(ref.onDisplayChange) !== 'function') 
			throw new Error('Missing function for printing');
		ref.onDisplayChange(mDisplay);
	}


	ref.clear = function () {
		all = 0;
		operator = '+';
		mDisplay = '0';

		clean = true;
		printDisplay();

	};
	ref.clear();

	ref.digit = function (value) {
		if (typeof (value) !== 'string')
			value = value.toString();
		if (value.length != 1 || value < '0' || value > '9') {
			console.error('Not a numerical value.');
			return;
		}
		if (clean || mDisplay == '0') {
			mDisplay = value;
			clean = false;
		} else
		mDisplay += value;
		printDisplay();
	};
	ref.addOperator = function (value) {
		if (!Number.isNaN(parseFloat(value)) && parseFloat(value) == value) {
			mDisplay = value;
			clean = false;
			printDisplay();
		} else {
			console.error('Not a numerical value.');
		}
	};
	ref.decimal = function () {
		if (clean) {
			mDisplay = '0.';
			clean = false;
		} else if (mDisplay.indexOf('.') === -1) {
			mDisplay += '.';
		} else
			console.warn('Already a ,');
			printDisplay();
	};
	
	ref.solve = function (value) {
		if ('+-*/='.indexOf(value) == -1) return;

		let operT = parseFloat(mDisplay);
		switch (operator) {
			case '+':
				all += operT;
				break;
			case '-':
				all -= operT;
				break;
			case '*':
				all *= operT;
				break;
			case '/':
				all /= operT;
				break;
		}

		mDisplay = parseFloat(all.toPrecision(15)).toString();
		printDisplay();
		operator = value;
		clean = true;
	};
}