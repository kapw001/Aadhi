package com.app.aadhi.common;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

public class DatePickerDialogFragmentNoRestrict extends DialogFragment implements OnDateSetListener {

	public interface OnDateSetListener {
		void onDateSet(Date date);
	}

	private OnDateSetListener mDateSetListener;
	private Date mDate;

	public DatePickerDialogFragmentNoRestrict() {
		// nothing to see here, move along
	}

	public void setListener(OnDateSetListener callback) {
		mDateSetListener = callback;
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Calendar cal = Calendar.getInstance();
		mDate = cal.getTime();

		DatePickerDialog dialog = new DatePickerDialog(getActivity(),
				this, cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
//		dialog.getDatePicker().setMinDate(mDate.getTime());

		return dialog;
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

		mDate = cal.getTime();
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		super.onDismiss(dialog);
		mDateSetListener.onDateSet(mDate);
	}
}