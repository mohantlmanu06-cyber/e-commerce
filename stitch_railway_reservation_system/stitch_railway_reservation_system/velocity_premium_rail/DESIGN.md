---
name: Velocity Premium Rail
colors:
  surface: '#051424'
  surface-dim: '#051424'
  surface-bright: '#2c3a4c'
  surface-container-lowest: '#010f1f'
  surface-container-low: '#0d1c2d'
  surface-container: '#122131'
  surface-container-high: '#1c2b3c'
  surface-container-highest: '#273647'
  on-surface: '#d4e4fa'
  on-surface-variant: '#c7c6cb'
  inverse-surface: '#d4e4fa'
  inverse-on-surface: '#233143'
  outline: '#909096'
  outline-variant: '#46464b'
  surface-tint: '#c5c6d1'
  primary: '#c5c6d1'
  on-primary: '#2e3039'
  primary-container: '#05070e'
  on-primary-container: '#767882'
  inverse-primary: '#5c5e67'
  secondary: '#ffb693'
  on-secondary: '#561f00'
  secondary-container: '#fe6b00'
  on-secondary-container: '#572000'
  tertiary: '#c1c5dd'
  on-tertiary: '#2b3042'
  tertiary-container: '#030617'
  on-tertiary-container: '#73778d'
  error: '#ffb4ab'
  on-error: '#690005'
  error-container: '#93000a'
  on-error-container: '#ffdad6'
  primary-fixed: '#e1e2ed'
  primary-fixed-dim: '#c5c6d1'
  on-primary-fixed: '#191b23'
  on-primary-fixed-variant: '#44464f'
  secondary-fixed: '#ffdbcc'
  secondary-fixed-dim: '#ffb693'
  on-secondary-fixed: '#351000'
  on-secondary-fixed-variant: '#7a3000'
  tertiary-fixed: '#dde1fa'
  tertiary-fixed-dim: '#c1c5dd'
  on-tertiary-fixed: '#161b2c'
  on-tertiary-fixed-variant: '#414659'
  background: '#051424'
  on-background: '#d4e4fa'
  surface-variant: '#273647'
  emerald-success: '#10B981'
  amber-warning: '#F59E0B'
  ruby-error: '#EF4444'
  glass-stroke: rgba(255, 255, 255, 0.12)
typography:
  display-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 40px
    fontWeight: '700'
    lineHeight: 48px
    letterSpacing: -0.02em
  headline-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 32px
    fontWeight: '700'
    lineHeight: 40px
  headline-lg-mobile:
    fontFamily: Plus Jakarta Sans
    fontSize: 24px
    fontWeight: '700'
    lineHeight: 32px
  headline-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 24px
    fontWeight: '600'
    lineHeight: 32px
  body-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 18px
    fontWeight: '400'
    lineHeight: 28px
  body-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  label-mono-lg:
    fontFamily: JetBrains Mono
    fontSize: 16px
    fontWeight: '500'
    lineHeight: 20px
    letterSpacing: 0.05em
  label-mono-sm:
    fontFamily: JetBrains Mono
    fontSize: 12px
    fontWeight: '500'
    lineHeight: 16px
    letterSpacing: 0.05em
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  base: 4px
  xs: 8px
  sm: 12px
  md: 16px
  lg: 24px
  xl: 32px
  container-margin: 20px
  gutter: 16px
---

## Brand & Style

The design system is engineered for the modern traveler who values efficiency and a premium experience. The brand personality is **sophisticated, precise, and forward-looking**. It evokes a sense of calm reliability through dark, immersive environments, accented by high-energy moments of action.

The visual style utilizes a **Glassmorphism-Corporate Hybrid** approach. This combines the structural integrity and professional layout of enterprise SaaS with the ethereal, high-end feel of frosted glass and deep background blurs. This aesthetic mimics the experience of looking through a sleek train window at night—utilizing translucency, subtle light refraction, and high-contrast information displays.

## Colors

The palette is anchored in a **Dark Mode** default to reduce eye strain during night travel and to provide a premium "executive" feel.

- **Primary & Tertiary:** Deep midnight shades (`#05070E` and `#0B1021`) form the foundation of the interface, creating depth and a "limitless" background.
- **Secondary (Accent):** High-visibility orange (`#FF6B00`) is reserved strictly for primary calls to action, progress indicators, and critical navigational cues.
- **Surface Neutrals:** Slate grays are used for secondary text and borders to maintain a hierarchy that doesn't compete with the deep background.
- **Functional Colors:** Success, Warning, and Error colors follow standard semantic expectations but are tuned for high luminosity against dark backgrounds.

## Typography

The typography system prioritizes legibility in high-motion environments.

- **Plus Jakarta Sans** is the primary typeface, chosen for its modern, friendly yet professional geometry. It is used for all headers and body copy.
- **JetBrains Mono** is introduced for technical data: seat numbers, platform IDs, train numbers, and ticket prices. Its fixed-width nature ensures that tabular data remains perfectly aligned and easily scannable.
- **Weight Strategy:** Heavy weights (600-700) are used for station names and departure times to ensure they anchor the layout.

## Layout & Spacing

This design system employs a **Fluid Layout** optimized for mobile-first interactions.

- **Grid:** A 4-column grid for mobile devices with 20px outer margins.
- **Spacing Rhythm:** An 8pt spatial system is used for most components, while a 4pt "micro-grid" is used for tight internal component spacing (e.g., icon-to-text labels).
- **Density:** The layout maintains high information density for schedules but utilizes generous vertical padding (24px+) between distinct journey segments to prevent visual fatigue.
- **Breakpoints:**
  - Mobile: < 600px
  - Tablet: 600px - 1024px (utilizes 8 columns)

## Elevation & Depth

Depth is conveyed through **backlight and translucency** rather than traditional drop shadows.

- **The Base:** The lowest level is the `#05070E` primary background.
- **The Glass Layer:** Cards and interactive containers use a semi-transparent background (`rgba(11, 16, 33, 0.7)`) with a `20px` backdrop blur. 
- **The Stroke:** To define edges on dark screens, "Glass" elements feature a subtle 1px inner border (`rgba(255, 255, 255, 0.12)`).
- **Z-Axis Hierarchy:**
  - Level 0: Background/Photography
  - Level 1: Standard Cards (Station info, history)
  - Level 2: Active Journey Cards (Floating elements)
  - Level 3: Modals and Navigation Bars

## Shapes

The shape language reflects the streamlined curves of high-speed rail. 

- **Primary Corners:** A `0.5rem` (8px) radius is standard for inputs and secondary buttons.
- **Large Components:** Cards and main containers use `1rem` (16px) for a softer, more modern feel.
- **Interactive Pills:** Chips and status indicators (e.g., "On Time") use fully rounded/pill shapes to distinguish them from structural elements.

## Components

- **Buttons:** 
  - Primary: Solid `#FF6B00` with white text, bold weight.
  - Secondary: Glass-style with a white border and backdrop blur.
- **Journey Cards:** Feature a vertical "rail" line on the left. Departure and arrival times use `label-mono-lg` for maximum clarity. The card itself utilizes the Glassmorphism style with a subtle white stroke.
- **Input Fields:** Darker than the card background with a `1px` border that glows with the primary orange when focused.
- **Status Chips:** Small, pill-shaped indicators with high-contrast backgrounds (Emerald for confirmed, Amber for waiting).
- **Live Tracker:** A dynamic component showing a train's progress. Use a gradient line from secondary orange to a transparent tail to indicate movement.
- **Navigation:** A bottom bar with a heavy backdrop blur (`30px`) and active icons highlighted in the secondary accent color.