package com.micmr0.todo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

public class FragmentManagerHelper {
    public static Fragment getTopFragment(FragmentManager pFragmentManager) {
        List<Fragment> fragments = pFragmentManager.getFragments();
        if (!fragments.isEmpty()) {
            return fragments.get(fragments.size() - 1);
        }

        return null;
    }

    public static Fragment getVisibleFragment(FragmentManager pFragmentManager) {
        List<Fragment> fragments = pFragmentManager.getFragments();
        for (int i = fragments.size() - 1; i >= 0; --i) {
            Fragment fragment = fragments.get(i);

            if(fragment.getTag() != null && fragment.getTag().equals("SupportLifecycleFragmentImpl"))   continue;

            if (fragment != null && fragment.getUserVisibleHint()) {
                return fragment;
            }
        }
        return null;
    }

    public static Fragment getVisibleFragment(FragmentManager pFragmentManager, Class<? extends Fragment> pClass) {
        List<Fragment> fragments = pFragmentManager.getFragments();
        for (int i = fragments.size() - 1; i >= 0; --i) {
            Fragment fragment = fragments.get(i);
            if (fragment != null && fragment.getUserVisibleHint() && pClass.isInstance(fragment)) {
                return fragment;
            }
        }
        return null;
    }
}