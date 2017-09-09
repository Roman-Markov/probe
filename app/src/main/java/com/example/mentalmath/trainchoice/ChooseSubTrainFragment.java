package com.example.mentalmath.trainchoice;

import android.app.Fragment;
import android.view.View;

/**
 * Base class for fragments which clarify the appropriate subtrain and launch activity for training
 * itself or launch one more activity for choice sub subtrain.
 */

public abstract class ChooseSubTrainFragment extends Fragment {

    public abstract void launchTrain(View v);
}
