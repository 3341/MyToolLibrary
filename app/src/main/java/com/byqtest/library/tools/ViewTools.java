package com.byqtest.library.tools;
import android.view.View;
import com.google.android.material.textfield.TextInputLayout;
import android.view.ViewGroup;

public class ViewTools {
    public static final int WIDTH_MATHPARENT_ID = 1;
    public static final int HEIGHT_MATCHPARENT_ID = 2;
	public static final int ALL_MATCH_MATCHPARENT_ID = 3;

    public static void setMatchParent(View v,int ids) {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams((ids == 1 || ids == ALL_MATCH_MATCHPARENT_ID)?-1:-2,(ids == 2 || ids == ALL_MATCH_MATCHPARENT_ID)?-1:-2);
        v.setLayoutParams(lp);
    }
}
