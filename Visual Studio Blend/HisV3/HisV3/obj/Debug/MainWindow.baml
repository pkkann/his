   M S B A M L   `   `   `  ����b  ^PresentationFramework.Aero2, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35hKclr-namespace:Microsoft.Windows.Themes;assembly=PresentationFramework.Aero2Microsoft.Windows.Themes  5   �   	 HisV3   HisV3.MainWindow   5   	   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     6F   ^ThemesKclr-namespace:Microsoft.Windows.Themes;assembly=PresentationFramework.Aero2     6�     � Title$  
MainWindow��5   	   $	��600��6   $	��900��6)    � AllowsTransparency .  � WindowStyle$
 None=�6O    � 	Resources 5         CloseButtonStyle �� 
TargetType �� Property �� Value �� 
TargetType'  !System.Windows.VisualStateManager   VisualStateGroups%  System.Windows.VisualStateGroup	   States   System.Windows.VisualState
   
Storyboard �� TargetProperty �� 
TargetName8  2System.Windows.Media.Animation.EasingColorKeyFrame �� KeyTime �� Value R� KeyTime R� Value �� Color ��Name ��Name J� SnapsToDevicePixels �� Triggers P� Property P� Value  toolButtonStyle  TextBoxStyle1  ItemTemplate d� 	IsChecked �� 
ResourceId �� TypeInTargetAssembly&   System.Windows.Controls.DataGrid �Name J� Opacity � Points �� Stretch �� 
TargetName  MyDatagridStyle  �� Padding! ��Name" � ColumnDefinitions# � RowDefinitions$   SelectAllCommand7  1System.Windows.Controls.DataGridHeadersVisibility
%   All& �� ConverterParameter!'   HeadersVisibilityConverter( �� 	Converter) �� RelativeSource* �� AncestorTypeG  ASystem.Windows.Controls.Primitives.DataGridColumnHeadersPresenter+  Name,   Column- ��Name. �� CanContentScroll/ ��Name0 �� ScrollableHeight1 � Maximum2 �� Orientation*3 �� #ComputedVerticalScrollBarVisibility4 �� ViewportHeight5 �� ViewportSize6 � Value7 �� Mode8 �� ScrollableWidth,9 �� %ComputedHorizontalScrollBarVisibility: �� ViewportWidth; �� Triggers< ]� 
Conditions= �� Property> �� Value  ColumnHeaderGripperStyle  DataGridColumnHeaderStyle1=  7System.Windows.Controls.Primitives.DataGridColumnHeader3	   -Microsoft.Windows.Themes.DataGridHeaderBorder?   CanUserSort@ 	  IsClickableA 	  	IsPressedB J� IsMouseOverC 	  	IsHoveredD   SortDirectionE 	  SortDirectionF   SeparatorBrushG 	  SeparatorBrushH   SeparatorVisibilityI 	  SeparatorVisibilityJ n�Name  ButtonStyle1��%�  &	        &	     &	 E    &	     (�� �    5�   
   $" DataGridSelectAllButtonStyle��"  )&	 +    &	 z    &	 �    7  7  &	 �    �� 5      " ��6#   .�5      ��  � �� 5	      �� 5
      " ��6   .R�5      �  5   	     5   
   CommonStates��.	 5        
Normal��.
 5      �� .��5      �� $* $(Shape.Fill).(SolidColorBrush.Color)�6*   $ 	rectangle��6k   .g�5        $ 0��6"   $ 	#FFA53D3D��6.   5      5      5        5      	MouseOver��.
 5      �� .��5      �� $* $(Shape.Fill).(SolidColorBrush.Color)�6*   $ 	rectangle��6k   .g�5        $ 0��6"   $ 	#FFDC3131��6.   5      T� 5      $ (UIElement.OpacityMask)�6+   $ contentPresenter��6_   ..�5      i� $ 0��6%    5      �� 5      $ Black��6    5      5       5!      5"        5#      Pressed��.
 5$      �� .��5%      �� $* $(Shape.Fill).(SolidColorBrush.Color)�6*   $ 	rectangle��6k   .g�5&        $ 0��6"   $ 	#FF943030��6.   5'      5(      5)        5*      Disabled��.
 5+      �� .��5,      �� $* $(Shape.Fill).(SolidColorBrush.Color)�6*   $ 	rectangle��6k   .g�5-        $ 0��6"   $ 	#FFCDCDCD��6.   5.      5/      50      51      .+�53   	   �� $ 	rectangle��6   
�������6&   �� 54   	   $ contentPresenter��6   #��z��64   ��. # z 6�   #��z��6�   55   	    56      P� 57   	    �@J�	IsFocused . 6&   P� 58   	    �@��IsDefaulted . 6(   P� 59   	    �@J�IsMouseOver . 6(   P� 5:   	    � �� . 6&   P� 5;   	    � {� .  6&   5=      5?      5@      �� 5A      " ��6"   .�5B      ��  � �� 5C      �� 5D      " ��6   .R�5E      �  5F   	     5G   
   CommonStates��.	 5H        
Normal��6     5I      	MouseOver��.
 5J      �� .��5K      �� $* $(Shape.Fill).(SolidColorBrush.Color)�6*   $ 	rectangle��6k   .g�5L        $ 0��6"   $ 	#FFE4E4E4��6.   5M      T� 5N      $ (UIElement.OpacityMask)�6+   $ contentPresenter��6_   ..�5O      i� $ 0��6%    61   Z� 5P      5Q      5R        5S      Pressed��.
 5T      �� .��5U      �� $* $(Shape.Fill).(SolidColorBrush.Color)�6*   $ 	rectangle��6k   .g�5V        $ 0��6"   $ 	#FFB4B4B4��6.   5W      5X      5Y        5Z      Disabled��.
 5[      �� .��5\      �� $* $(Shape.Fill).(SolidColorBrush.Color)�6*   $ 	rectangle��6k   .g�5]        $ 0��6"   $ 	#FFCDCDCD��6.   5^      5_      5`      5a      .+�5c   	   �� $ 	rectangle��6   
�������6&   �� 5d   	   $ contentPresenter��6   #��z��64   ��. # z 6�   #��z��6�   5e   	    5f      P� 5g   	    �@J�	IsFocused . 6&   P� 5h   	    �@��IsDefaulted . 6(   P� 5i   	    �@J�IsMouseOver . 6(   P� 5j   	    � �� . 6&   P� 5k   	    � {� .  6&   5m      5o      5p      �� 5q      " ��6    .�5r      ��  � �� 5s      �� 5t      " ��6   .R�5u      � .+�5v   	   �� ���	#FFF4F4F5
���   �6$   5w   	   5x      5z      5{      �� 5|      .R�5}      �� .+�5~      �� ��6   �� *	Property1+�� 5       6   �� *	Property2+5�      5�      �� 5�      " ��6y   .�5�      ��  � �� 5�      �� 5�      " ��6   .R�5�      � .+�5�   	   �� $ Border��6   #��� ��6#    . 6d   � 5�   	   $ Arrow��6   
���   �$��Right=�6-   $��8,8,3,3q�6I   $
 0.15Q�6Z    �   ��� ��� ��� ��� $ Uniform=�6�   $��Bottom=�6�   5�   	    5�      P� 5�   	    �@J�IsMouseOver . . �5�   
   �� $ Border��6#   # � ��67    � ��5�   
   P� 5�   	    � �� . . �5�   
   �� $ Border��6!   # � ��65    � ��5�   
   P� 5�   	    � {� .  . �5�   
   �� $ Arrow��6'    � y�$ 	Collapsed=�6:   5�   
   5�      5�      5�      �� 5�      "  6"   .�5�      ��  � ��# � ��6"   �� 5�       � ��# � ��6"   �� 5�       � �� �	#FF688CAF6#   �� 5�       � ��$ 1q�6'   �� 5�        �@ RowDetailsVisibilityMode$ VisibleWhenSelected=�60   �� 5�       � �� . 65   �� 5�       �@��PanningMode$
 Both=�60   �� 5�       �@��IsFlicksEnabled .  6.   �� 5�       � �� 5�      �� 5�      "  6   .R�5�      �� #��z��6   #��z��6;   #  z��6o    . ._�5�   	   �� $! DG_ScrollViewer��6   |�.  ��5�   
   �� 5�      " ��6   .R�5�      � " 5�      �� 5�      $
��Auto �6   �� 5�      $��* �6   �� 5�      $
��Auto �6   # 5�      �� 5�      $
��Auto �6   �� 5�      $��* �6   �� 5�      $
��Auto �6   .+�5�      �� #��Z$ 6   |�.  ��6U   C� *�� $" DataGridSelectAllButtonStyle��"  +y�6�   �� *HeadersVisibility+#& Z% #( Z' ) �� "*  ��6�  �� *CellsPanelHorizontalOffset+) �� "*    5�      $!+ PART_ColumnHeadersPresenter��6,   $��1��6Q   y�6a   �� *HeadersVisibility+#& Z, #( Z' ) �� "*  �� 5�      $!- PART_ScrollContentPresenter��6$   #. z��6I   $��2��6   $��1��6�   �� 5�      $/ PART_VerticalScrollBar��6   $��2��67   #1 z0 6G   $2 Vertical=�6t   $��1��6�   #y�z3 6�   #5 z4 6:  6 6�   �� *VerticalOffset+$7 OneWay=�) �� *TemplatedParent+6:  � 5�      $��1��6   $��2��6"   " 5�      �� 5�      ��6    �� **(NonFrozenColumnsViewportHorizontalOffset+) �� "*  �� 5�      $��* �6    .+�5�      �� $/ PART_HorizontalScrollBar��6   $��1��6:   #1 z8 6J   $2 
Horizontal=�6v   #y�z9 6�   #5 z: 65  6 6�   �� *HorizontalOffset+$7 OneWay=�) �� *TemplatedParent+65  5�      5�      5�      .��5�   
   �� # z 6   5�   
   5�   	   5�      5�      ; 5�      ]� 5�      < 5�      �� 5�      = �@��
IsGrouping> . 6(   �� 5�      "= �@%�IsVirtualizingWhenGrouping> .  6J   ./�5�      ��  � �� .  67   5�      5�      �� 5�      " n�6+   .�5�      ��  � ��$ 8��6   �� 5�       � ��
 ���� 6"   �� 5�       �@�Cursor$ SizeWE��6   �� 5�       � �� 5�      �� 5�      " n�6   .R�5�      �� #��z��6   #  z��69   5�      5�      5�      �� 5�      "  6-   .�5�      ��  � ��$ Center=�60   �� 5�       � �� 5�      �� 5�      "  6   .R�5�      � .+�5�   	   	  #��z��6%   #��z��6Q   #@ z? 6�   #A z��6�   #C zB 6�   #  z��6  #E zD 6'  #G zF 6W  #I zH 6�  ���	#FFE43636._�5�   
   �� #��z��6   ��. # z 6y   #��z��6�   5�   
   n� 5�   	   $J PART_LeftHeaderGripper��6   $
��Left=�6/   8��  6J   n� 5�   	   $J PART_RightHeaderGripper��6   $��Right=�60   8�� 6L   5�   	   5�      5�      5�      �� 5�      " ��6   .�5�      ��  � �� 5�      �� 5�      " ��6   .R�5�      �  5�   	     5�   
   CommonStates��.	 5�        
Normal��6     5�      	MouseOver��.
 5�      �� .��5�      �� $* $(Shape.Fill).(SolidColorBrush.Color)�6*   $ 	rectangle��6k   .g�5�        $ 0��6"   $ 	#FF2F2FD3��6.   5�      5�      5�        5�      Pressed��6     5�      Disabled��6   5�      .+�5  	   �� $ 	rectangle��6   ���	#FFF4F4F5
���   �67   �� 5  	   #��z��6   ��. # z 6x   #��z��6�   5  	    5     P� 5  	    �@J�	IsFocused . 6&   P� 5  	    �@��IsDefaulted . 6(   P� 5  	    �@J�IsMouseOver . 6(   P� 5  	    � �� . 6&   P� 5	  	    � {� .  6&   5     5     5     5     .��5     �� ���	#FF2F2F2F$��1q�6"   $
��Left=�66   $	��600��6Q   $	��Top=�6^   $	��900��6v   ._�5     � K � DataContextK 6	   �� .+�5     �� $��X��6   $
��Left=�6   $��20��63   $��
856,10,0,0q�6?   #���   6S   $	��Top=�6~   $��30��6�   $��Segoe UI)�6�   
�������6�   �� 5     $��O��6   $
��Left=�6   $��20��63   $��
826,10,0,0q�6?   #���  6S   $	��Top=�6}   $��30��6�   ���	#FF8B8B8B6�   �� 5     $��_��6   $
��Left=�6   $��20��63   $��
796,10,0,0q�6?   #���  6S   $	��Top=�6}   $��30��6�   ���	#FF8B8B8B6�   �� 5     $
��Left=�6   $��23��6(   $��	10,10,0,0q�64   L �� TextWrapping$
L Wrap=�6G   $	��Top=�6[   $��282.234��6s   M � ToolTip$M Search��6�   N J� RenderTransformOrigin$N 1,0.5&�6�   5     5     5     