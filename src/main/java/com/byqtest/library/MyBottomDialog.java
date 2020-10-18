package com.byqtest.library;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.view.Gravity;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import android.content.Context;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import android.widget.LinearLayout;
import com.google.android.material.textfield.TextInputEditText;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.Editable;
import android.util.TypedValue;
import com.byqtest.library.tools.ViewTools;

public class MyBottomDialog {
	private Context context;
    private BottomSheetDialog bottomSheetDialog;
    private View mView;
    private View.OnClickListener dismissClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View p1) {
            dismiss();
        }
    };

    public MyBottomDialog(Context context) {
        this.context = context;
        bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

	public void setBottomSheetDialog(BottomSheetDialog bottomSheetDialog) {
		this.bottomSheetDialog = bottomSheetDialog;
	}

	public BottomSheetDialog getBottomSheetDialog() {
		return bottomSheetDialog;
	}

    public void setContentView(View v) {
        bottomSheetDialog.setContentView(v);
    }

    public void show(View v) {
        mView = v;
        setContentView(v);
        bottomSheetDialog.show();
        bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet)
            .setBackgroundResource(android.R.color.transparent);
    }

    public void dismiss() {
        bottomSheetDialog.dismiss();
    }

    public <T extends View> T findViewById(int id) {
        return mView.findViewById(id);
    }

    public void setButton1(String text,View.OnClickListener clicklistener) {
        Button btn = mView.findViewById(R.id.dialog_inputs_layoutButton1);
        btn.setText(text);
        if(text.isEmpty()) {
            btn.setVisibility(View.INVISIBLE);
            btn.setClickable(false);
        } else {
            btn.setVisibility(View.VISIBLE);
            btn.setClickable(true);
        }
        if(clicklistener == null) clicklistener = dismissClickListener;
        btn.setOnClickListener(clicklistener);
    }

    public void setButton2(String text,View.OnClickListener clicklistener) {
        Button btn = mView.findViewById(R.id.dialog_inputs_layoutButton2);
        btn.setText(text);
        if(text.isEmpty()) {
            btn.setVisibility(View.INVISIBLE);
            btn.setClickable(false);
        } else {
            btn.setVisibility(View.VISIBLE);
            btn.setClickable(true);
        }
        if(clicklistener == null) clicklistener = dismissClickListener;
        btn.setOnClickListener(clicklistener);
    }

    public void setButton3(String text,View.OnClickListener clicklistener) {
        Button btn = mView.findViewById(R.id.dialog_inputs_layoutButton3);
        btn.setText(text);
        if(text.isEmpty()) {
            btn.setVisibility(View.INVISIBLE);
            btn.setClickable(false);
        } else {
            btn.setVisibility(View.VISIBLE);
            btn.setClickable(true);
        }
        if(clicklistener == null) clicklistener = dismissClickListener;
        btn.setOnClickListener(clicklistener);
    }

    private View getDefaultView() {
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_inputs_layout,null,false);
        ViewTools.setMatchParent(v,1);
        return v;
    }

    private void ifViewStringEmptyVisable(View v,String str) {
        if(str.isEmpty()) {
            v.setVisibility(View.INVISIBLE);
            v.setEnabled(false);
        } else {
            v.setVisibility(View.VISIBLE);
            v.setEnabled(true);
        }
    }

    public void showMessageDialog(String title,String msg) {
        show(getDefaultView());
        TextView tv = findViewById(R.id.dialog_inputs_layoutTextView_title);
        tv.setText(title);
        ifViewStringEmptyVisable(tv,title);
        tv = findViewById(R.id.dialog_inputs_layoutTextView_subtitle);
        ifViewStringEmptyVisable(tv,msg);
        tv.setText(msg);

        if(title.isEmpty() && msg.isEmpty())  {
            System.out.println("Content Both empty! Can't show dialog.");
            dismiss();
            return;
        }
    }

    /**
	 *    Inputs:{"hint","content","InputType"}
	 **/
    public void showInputsDialog(String[][] inputs,String title,String msg) {
        showMessageDialog(title,msg);

        LinearLayout lin = findViewById(R.id.dialog_inputs_layoutLinearLayout_container);
        for(final String[] s : inputs) {
            TextInputLayout layout = (TextInputLayout) LayoutInflater.from(context).inflate(R.layout.textinputedittext_layout,null,false);
            ViewTools.setMatchParent(layout,1);
            layout.setHint(s[0]);
            TextInputEditText edit = layout.findViewById(R.id.textinputedittext_layout_TextInputEditText);
            edit.setText(s[1]);
            if(s[2] != null) edit.setInputType(Integer.valueOf(s[2]));
            edit.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4) {
                    }

                    @Override
                    public void onTextChanged(CharSequence c, int p2, int p3, int p4) {
                        s[1] = c.toString();
                    }

                    @Override
                    public void afterTextChanged(Editable p1) {
                    }
				});
            lin.addView(layout);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) layout.getLayoutParams();
            int dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,15,context.getResources().getDisplayMetrics());
            lp.setMargins(0,dp,0,dp);
        }
    }
}


