/*
 * Tencent is pleased to support the open source community by making QMUI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uniah.mobile.widget.pullRefreshLayout;

import com.uniah.mobile.widget.pullRefreshLayout.UniPullRefreshLayout.OffsetCalculator;

/**
 * RefreshView 永远和 TargetView 保持一定的距离(这个距离由刷新时RefreshView居中算出)
 *
 */

public class UniDefaultRefreshOffsetCalculator implements OffsetCalculator {

    @Override
    public int calculateOffset(int refreshInitOffset, int refreshEndOffset, int refreshViewHeight, int targetCurrentOffset, int targetInitOffset, int targetRefreshOffset) {
        int distance = targetRefreshOffset / 2 + refreshViewHeight / 2;
        int max = targetCurrentOffset - refreshViewHeight;
        return Math.min(max, targetCurrentOffset - distance);
    }
}
